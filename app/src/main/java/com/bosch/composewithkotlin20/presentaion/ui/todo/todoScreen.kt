package com.bosch.composewithkotlin20.presentaion.ui.todo

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.todo.common.AddTaskDialog
import com.bosch.composewithkotlin20.presentaion.ui.todo.common.TaskItem
import com.bosch.composewithkotlin20.presentaion.ui.todo.common.TaskItemDone
import com.bosch.composewithkotlin20.presentaion.ui.todo.common.ViewTask
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Composable
fun TodoScreen(viewModel: TodoViewModel) {

    val uiState by viewModel.todoUiState.collectAsState()
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(pageCount = { TodoTabs.entries.size })
    val selectedTab = remember { derivedStateOf { pagerState.currentPage } }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TabRow(
            selectedTabIndex = selectedTab.value, modifier = Modifier.fillMaxWidth()
        ) {
            TodoTabs.entries.forEachIndexed { index, tab ->

                Tab(modifier = Modifier
                    .height(60.dp)
                    .padding(bottom = 15.dp, top = 20.dp),
                    selected = selectedTab.value == index,
                    selectedContentColor = MaterialTheme.colorScheme.outline,
                    unselectedContentColor = MaterialTheme.colorScheme.outline,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(tab.ordinal)
                        }
                    },
                    text = { Text(text = tab.title) },
                    icon = {
                        Image(
                            painter = if (selectedTab.value == index) painterResource(id = tab.selectedIcon) else painterResource(
                                id = tab.unselectedIcon
                            ), contentDescription = "",
                            colorFilter = if (selectedTab.value == index) null else ColorFilter.tint(
                                Color.Gray
                            )
                        )
                    })


            }
        }
        HorizontalPager(
            state = pagerState, modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) { pageIndex ->
            when (TodoTabs.entries[pageIndex]) {
                TodoTabs.List -> TaksList(uiState, viewModel)
                TodoTabs.Done -> Completedtask(uiState)
            }
        }
        FloatingActionButton(
            modifier = Modifier.padding(24.dp),
            containerColor = MaterialTheme.colorScheme.primary,
            onClick = { viewModel.todoUiEvent(event = TodoUIEvent.ToggleDialog) },
            shape = CircleShape
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }


    }



    if (uiState.showDialog) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black.copy(alpha = 0.2f))
        ) {
            AddTaskDialog(onDismiss = {
                viewModel.todoUiEvent(TodoUIEvent.ToggleDialog)
            }, onSave = {
                viewModel.todoUiEvent(TodoUIEvent.AddTask(it))
                viewModel.todoUiEvent(TodoUIEvent.ToggleDialog)

            })
        }
    }

    if (uiState.openTaskDialog) {

        uiState.selectedTask?.let { item ->
            ViewTask(tasks = item, onDismiss = {
                viewModel.todoUiEvent(TodoUIEvent.CloseTaskDialog)
            }, onComplete = {

                val index = uiState.tasksList.indexOf(item)
                viewModel.todoUiEvent(TodoUIEvent.CompletedTask(index))
                viewModel.todoUiEvent(TodoUIEvent.CloseTaskDialog)

            })
        }
    }
    if (uiState.loading) {

    }
    if (uiState.showError) {

    }
}


@Composable
private fun Completedtask(uiState: HomeUiState) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        val completedTasks = uiState.tasksList.filter { it.completed }
        items(completedTasks.size) { index ->
            TaskItemDone(task = completedTasks[index])
        }
    }
}

@Composable
private fun TaksList(
    uiState: HomeUiState, viewModel: TodoViewModel
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        val uncompletedTasks = uiState.tasksList.filter { !it.completed }
        items(uncompletedTasks.size) { index ->
            TaskItem(task = uncompletedTasks[index], onTaskCompleted = { checked ->
                if (checked) {
                    viewModel.todoUiEvent(event = TodoUIEvent.CompletedTask(index))
                }
            }, onClick = {
                viewModel.todoUiEvent(
                    event = TodoUIEvent.OpenTaskDialog(
                        uncompletedTasks[index]
                    )
                )
            })
        }
    }
}


enum class TodoTabs(
    val selectedIcon: Int, val unselectedIcon: Int, val title: String
) {
    List(
        R.drawable.task_svgrepo_com,
        R.drawable.task_svgrepo_com,
        "My Task"
    ),
    Done(
        R.drawable.task_svgrepo_com__1_,
        R.drawable.task_svgrepo_com__1_,
        "Completed"
    )
}


@Serializable
object TodoScreen


@Preview
@Composable
fun TodoScreenPreview() {

}

@Preview
@Composable
fun TaskItemPreview() {

}