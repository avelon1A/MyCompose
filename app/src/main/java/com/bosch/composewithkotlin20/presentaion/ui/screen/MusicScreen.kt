package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.data.model.data.Audio
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.AudioViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.UIEvents
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlin.math.floor

@Composable
fun MusicScreenContent(viewModel: AudioViewModel){
	MusicScreen(progress = viewModel.progress,
		onProgress = { viewModel.onUiEvents(UIEvents.SeekTo(it)) },
		isAudioPlaying = viewModel.isPlaying,
		audiList = viewModel.audioList,
		currentPlayingAudio = viewModel.currentSelectedAudio,
		onStart = {
			viewModel.onUiEvents(UIEvents.PlayPause)
		},
		onItemClick = {
			viewModel.onUiEvents(UIEvents.SelectedAudioChange(it))
		},
		onNext = {
			viewModel.onUiEvents(UIEvents.SeekToNext)
		})
}

@Composable
fun MusicScreen(
	progress: Float,
	onProgress: (Float) -> Unit,
	isAudioPlaying: Boolean,
	currentPlayingAudio: Audio,
	audiList: List<Audio>,
	onStart: () -> Unit,
	onItemClick: (Int) -> Unit,
	onNext: () -> Unit,
) {
	Scaffold(
		bottomBar = {
			BottomBarPlayer(
				progress = progress,
				onProgress = onProgress,
				audio = currentPlayingAudio,
				onStart = onStart,
				onNext = onNext,
				isAudioPlaying = isAudioPlaying
			)
		}
	) {
		LazyColumn(
			contentPadding = it
		) {
			itemsIndexed(audiList) { index, audio ->
				AudioItem(
					audio = audio,
					onItemClick = { onItemClick(index) }
				)
			}
		}
	}
	
}

@Composable
fun AudioItem(
	audio: Audio,
	onItemClick: () -> Unit,
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(12.dp)
			.clickable {
				onItemClick()
			},
	) {
		Row(
			verticalAlignment = Alignment.CenterVertically,
			modifier = Modifier.padding(8.dp)
		) {
			Column(
				modifier = Modifier
					.weight(1f)
					.padding(8.dp),
				verticalArrangement = Arrangement.Center
			) {
				Spacer(modifier = Modifier.size(4.dp))
				Text(
					text = audio.displayName,
					style = MaterialTheme.typography.titleLarge,
					overflow = TextOverflow.Clip,
					maxLines = 1
				)
				Spacer(modifier = Modifier.size(4.dp))
				Text(
					text = audio.artist,
					style = MaterialTheme.typography.bodySmall,
					maxLines = 1,
					overflow = TextOverflow.Clip
				)
				
			}
			Text(
				text = timeStampToDuration(audio.duration.toLong())
			)
			Spacer(modifier = Modifier.size(8.dp))
		}
		
	}
}

private fun timeStampToDuration(position: Long): String {
	val totalSecond = floor(position / 1E3).toInt()
	val minutes = totalSecond / 60
	val remainingSeconds = totalSecond - (minutes * 60)
	return if (position < 0) "--:--"
	else "%d:%02d".format(minutes, remainingSeconds)
}


@Composable
fun BottomBarPlayer(
	progress: Float,
	onProgress: (Float) -> Unit,
	audio: Audio,
	isAudioPlaying: Boolean,
	onStart: () -> Unit,
	onNext: () -> Unit,
) {
	val sliderProgress = remember { mutableStateOf(progress) }
	LaunchedEffect(sliderProgress.value) {
		delay(50)
		onProgress(sliderProgress.value)
	}
	BottomAppBar(
		content = {
			Column(
				modifier = Modifier.padding(8.dp)
			) {
				Row(
					modifier = Modifier
						.fillMaxWidth()
						.height(56.dp),
					horizontalArrangement = Arrangement.SpaceBetween,
					verticalAlignment = Alignment.CenterVertically
				) {
					ArtistInfo(
						audio = audio,
						modifier = Modifier.weight(1f),
					)
					MediaPlayerController(
						isAudioPlaying = isAudioPlaying,
						onStart = onStart,
						onNext = onNext
					)
					Slider(
						value = sliderProgress.value,
						onValueChange = { sliderProgress.value = it},
						valueRange = 0f..100f
					)
					
				}
			}
		}
	)
}

@Composable
fun MediaPlayerController(
	isAudioPlaying: Boolean,
	onStart: () -> Unit,
	onNext: () -> Unit,
) {
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.height(56.dp)
			.padding(4.dp)
	) {
		PlayerIconItem(
			icon = if (isAudioPlaying) Icons.Default.PlayArrow
			else Icons.Default.PlayArrow
		) {
			onStart()
		}
		Spacer(modifier = Modifier.size(8.dp))
		Icon(
			imageVector = Icons.Default.Star,
			modifier = Modifier.clickable {
				onNext()
			},
			contentDescription = null
		)
	}
}

@Composable
fun ArtistInfo(
	modifier: Modifier = Modifier,
	audio: Audio,
) {
	Row(
		modifier = modifier.padding(4.dp),
		verticalAlignment = Alignment.CenterVertically
	) {
		PlayerIconItem(
			icon = Icons.Default.Settings,
			borderStroke = BorderStroke(
				width = 1.dp,
				color = MaterialTheme.colorScheme.onSurface
			)
		) {}
		Spacer(modifier = Modifier.size(4.dp))
		Column {
			Text(
				text = audio.title,
				fontWeight = FontWeight.Bold,
				style = MaterialTheme.typography.titleLarge,
				overflow = TextOverflow.Clip,
				modifier = Modifier.weight(1f),
				maxLines = 1
			)
			Spacer(modifier = Modifier.size(4.dp))
			Text(
				text = audio.artist,
				fontWeight = FontWeight.Normal,
				style = MaterialTheme.typography.bodySmall,
				overflow = TextOverflow.Clip,
				maxLines = 1
			)
		}
	}
}

@Composable
fun PlayerIconItem(
	modifier: Modifier = Modifier,
	icon: ImageVector,
	borderStroke: BorderStroke? = null,
	backgroundColor: Color = MaterialTheme.colorScheme.surface,
	color: Color = MaterialTheme.colorScheme.onSurface,
	onClick: () -> Unit,
) {
	Surface(
		shape = CircleShape,
		border = borderStroke,
		modifier = Modifier
			.clip(CircleShape)
			.clickable {
				onClick()
			},
		contentColor = color,
		color = backgroundColor
	) {
		Box(
			modifier = Modifier.padding(4.dp),
			contentAlignment = Alignment.Center,
		) {
			Icon(
				imageVector = icon,
				contentDescription = null
			)
		}
	}
}

@Serializable
object MusicScreenContent