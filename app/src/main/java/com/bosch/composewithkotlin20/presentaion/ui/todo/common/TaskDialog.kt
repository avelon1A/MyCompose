package com.bosch.composewithkotlin20.presentaion.ui.todo.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.todo.Tasks

@Composable
fun TaskItemDone(task: Tasks) {
	val checkedState = remember { mutableStateOf(false) }
	ConstraintLayout(
		modifier = Modifier
			.fillMaxWidth()
			.padding(10.dp)
			.background(shape = RoundedCornerShape(10.dp), color = Color(0xFFFF4040))
			.padding(5.dp)
			.clickable {}
	) {
		val (checkbox, taskName, fileIcon, fileCount, progressbar) = createRefs()
		Checkbox(checked = checkedState.value, onCheckedChange = {},enabled = false,
			modifier = Modifier.constrainAs(checkbox) {
				start.linkTo(parent.start)
				top.linkTo(parent.top)
				bottom.linkTo(parent.bottom)
			})
		
		Text(text = task.taskName, modifier = Modifier.constrainAs(taskName) {
			start.linkTo(checkbox.end)
			top.linkTo(parent.top)
			bottom.linkTo(parent.bottom)
		})
		Image(painter = painterResource(id = R.drawable.file), contentDescription = "file icon",
			modifier = Modifier
				.height(23.dp)
				.width(23.dp)
				.padding(1.dp)
				.constrainAs(fileIcon) {
					end.linkTo(parent.end)
					top.linkTo(parent.top)
					bottom.linkTo(parent.bottom)
				})
		Text(
			text = task.taskFiles.size.toString(),
			color = MaterialTheme.colorScheme.surface, fontSize = 12.sp,
			modifier = Modifier.constrainAs(fileCount) {
				start.linkTo(fileIcon.start, margin = 7.dp)
				bottom.linkTo(fileIcon.bottom, margin = 3.dp)
			})
	}
}
