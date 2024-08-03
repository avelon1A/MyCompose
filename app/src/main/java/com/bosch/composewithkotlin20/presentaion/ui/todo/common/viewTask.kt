package com.bosch.composewithkotlin20.presentaion.ui.todo.common

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.bosch.composewithkotlin20.presentaion.ui.todo.Tasks

@Composable
fun ViewTask(
	tasks: Tasks,
	onDismiss: () -> Unit,
	onComplete: () -> Unit
) {
	Dialog(onDismissRequest = onDismiss) {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.wrapContentHeight()
				.background(
					color = Color.White,
					shape = RoundedCornerShape(8.dp)
				)
				.padding(16.dp)
		) {
			Text(
				text = tasks.taskName,
				fontWeight = FontWeight.Bold,
			)
			Text(
				text = tasks.taskDetails,
				fontWeight = FontWeight.Bold,
			)
			LazyColumn {
				items(tasks.taskFiles.size) {
					AttachmentItems(tasks.taskFiles[it])
				}
			}
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(10.dp),
				horizontalArrangement = Arrangement.SpaceEvenly,
				verticalAlignment = Alignment.CenterVertically
			) {
				IconButton(
					onClick = onDismiss, modifier = Modifier
						.fillMaxWidth()
						.padding(20.dp)
						.border(3.dp, Color.Red, shape = RoundedCornerShape(10.dp))
						.weight(1f)
				)
				{
					Icon(
						imageVector = Icons.Default.Close,
						contentDescription = "close button",
						tint = Color.Red,
						modifier = Modifier.fillMaxSize()
					)
					
				}
				IconButton(
					onClick = onComplete, modifier = Modifier
						.fillMaxWidth()
						.padding(20.dp)
						.border(3.dp, Color.Green, shape = RoundedCornerShape(10.dp))
						.weight(1f)
				)
				{
					Icon(
						imageVector = Icons.Default.Check,
						contentDescription = "Complete button",
						tint = Color.Green,
						modifier = Modifier.fillMaxSize()
					)
					
				}
			}
		}
	}
}

@Composable
fun AttachmentItems(url: String) {
	val expand = remember {
		mutableStateOf(false)
	}
	Box(
		modifier = Modifier
			.fillMaxWidth()
			.wrapContentHeight(), Alignment.TopEnd
	) {
		if (expand.value) {
			ThemeImageView(
				url = url, modifier = Modifier
					.fillMaxSize()
					.height(160.dp)
					.background(
						color = Color.Transparent,
						shape = RoundedCornerShape(10.dp)
					)
			)
		}
		var rowBg = Color.Transparent
		var iconTint = Color.Black
		
		if(expand.value){
		
			rowBg = Color.Black.copy(alpha = 0.5f)
			iconTint = Color.White
		}
		Row(modifier = Modifier.fillMaxWidth()
			.wrapContentHeight()
			.clickable{
			expand.value = !expand.value
		}
			.background(
				color = rowBg,
				shape = RoundedCornerShape(10.dp)
			)
			.padding(10.dp),
			verticalAlignment = Alignment.CenterVertically,
			horizontalArrangement = Arrangement.SpaceBetween
		){
			
			Icon(
				imageVector = Icons.Default.Assessment,
				contentDescription = "Complete button",
				tint = Color.Green,
				modifier = Modifier.fillMaxSize()
			)
			Icon(
				imageVector = Icons.Default.KeyboardArrowDown,
				contentDescription = "Complete button",
				tint = iconTint,
				modifier = Modifier.height(20.dp).width(20.dp)
			)
		
		}
		
		
		
	}
}
