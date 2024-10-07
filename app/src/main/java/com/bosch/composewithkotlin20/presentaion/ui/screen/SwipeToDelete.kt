package com.bosch.composewithkotlin20.presentaion.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.EmailMessage
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.SwipeToDeleteViewModel
import kotlinx.serialization.Serializable


@Composable
fun  SwipeToDelete(emailViewModel: SwipeToDeleteViewModel) {
    val messages by emailViewModel.messagesState.collectAsState()

   
            LazyColumn(
                modifier = Modifier
                    .padding()
                    .fillMaxSize(),
                contentPadding = PaddingValues(vertical = 12.dp),
            ) {
                itemsIndexed(
                    items = messages,
                    key = { _, item -> item.hashCode() }
                ) { _, emailContent ->
                    EmailItem(emailContent, onRemove = emailViewModel::removeItem)
                }
            }
        }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmailItem(
    emailMessage: EmailMessage,
    modifier: Modifier = Modifier,
    onRemove: (EmailMessage) -> Unit
) {
    val context = LocalContext.current
    val currentItem by rememberUpdatedState(emailMessage)
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = {
            when(it) {
                SwipeToDismissBoxValue.StartToEnd -> {
                    onRemove(currentItem)
                    Toast.makeText(context, "Item deleted", Toast.LENGTH_SHORT).show()
                }
                SwipeToDismissBoxValue.EndToStart -> {
                    onRemove(currentItem)
                    Toast.makeText(context, "Item archived", Toast.LENGTH_SHORT).show()
                }
                SwipeToDismissBoxValue.Settled -> return@rememberSwipeToDismissBoxState false
            }
            return@rememberSwipeToDismissBoxState true
        },
        positionalThreshold = { it * .25f }
    )
    SwipeToDismissBox(
        state = dismissState,
        modifier = modifier,
        backgroundContent = { DismissBackground(dismissState)},
        content = {
            EmailMessageCard(emailMessage)
        })
}

@Composable
fun EmailMessageCard(emailMessage: EmailMessage) {
    ListItem(
        modifier = Modifier.clip(MaterialTheme.shapes.small),
        headlineContent = {
            Text(
                emailMessage.sender,
                style = MaterialTheme.typography.titleMedium
            )
        },
        supportingContent = {
            Text(
                emailMessage.message,
                style = MaterialTheme.typography.bodySmall
            )
        },
        leadingContent = {
            Icon(
                Icons.Filled.Person,
                contentDescription = "person icon",
                Modifier
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primaryContainer)
                    .padding(10.dp)
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    val color = when (dismissState.dismissDirection) {
        SwipeToDismissBoxValue.StartToEnd -> Color(0xFFFF1744)
        SwipeToDismissBoxValue.EndToStart -> Color(0xFF1DE9B6)
        SwipeToDismissBoxValue.Settled -> Color.Transparent
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )
        Spacer(modifier = Modifier)
        Icon(
            painter = painterResource(R.drawable.task_svgrepo_com),
            contentDescription = "Archive"
        )
    }
}

@Serializable
object SwipeToDelete
    
