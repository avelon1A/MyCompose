package com.bosch.composewithkotlin20.presentaion.ui.screen.supabase

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.data.model.data.Video
import com.bosch.composewithkotlin20.presentaion.ui.common.AppBar
import com.bosch.composewithkotlin20.presentaion.ui.screen.ExandCards
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.serialization.Serializable



@Composable
fun SupabaseVideoPlayer(
    navController: NavHostController,
    supabaseVideoPlayerViewModel: SupabaseVideoPlayerViewModel
) {
    val videos by supabaseVideoPlayerViewModel.videos.collectAsState()
    val configuration = LocalConfiguration.current

    var selectedVideoUrl by rememberSaveable { mutableStateOf<String?>(null) }
    var isPlaying by rememberSaveable { mutableStateOf(false) }

    val isLandscape = configuration.orientation == android.content.res.Configuration.ORIENTATION_LANDSCAPE
    val appBar: @Composable () -> Unit = if (isLandscape) {
        {}
    } else {
        { AppBar(R.drawable.arrow_back, navController) }
    }

    Scaffold(
        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
        topBar = { appBar() },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    // Remove innerPadding in landscape mode
                    .padding(if (isLandscape) 0.dp else innerPadding.calculateTopPadding())
                    .background(MaterialTheme.colorScheme.surface)
                    .fillMaxSize()
            ) {
                if (selectedVideoUrl == null) {
                    VideoList(
                        videos = videos,
                        onVideoSelected = { url ->
                            selectedVideoUrl = url
                        },
                        modifier = Modifier.height(if (isLandscape) 100.dp else 250.dp),
                        smallSize = false
                    )
                } else {
                    VideoPlayer(
                        url = selectedVideoUrl!!,
                        onPlayingStateChanged = { playing -> isPlaying = playing }
                    )
                    VideoList(
                        videos = videos,
                        onVideoSelected = { url ->
                            selectedVideoUrl = url
                        },
                        modifier = Modifier.height(if (isLandscape) 100.dp else 100.dp),
                        smallSize = true
                    )
                }
            }
        }
    )
}


@Composable
fun VideoPlayer(url: String, onPlayingStateChanged: (Boolean) -> Unit) {
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val modifier by remember { mutableStateOf(Modifier) }
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            playWhenReady = true
        }
    }
    when (configuration.orientation) {
        android.content.res.Configuration.ORIENTATION_LANDSCAPE -> {
            modifier.fillMaxSize()
        }
        else -> {
            modifier.height(250.dp)
        }
    }

    DisposableEffect(exoPlayer) {

        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_READY -> {

                        onPlayingStateChanged(exoPlayer.isPlaying)
                    }
                    Player.STATE_ENDED -> {
                        // Playback has ended
                        onPlayingStateChanged(false)
                    }
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                onPlayingStateChanged(isPlaying)
            }
        }

        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }

    AndroidView(
        factory = { PlayerView(context).apply { player = exoPlayer } },
        modifier = modifier
            .fillMaxWidth()

    )
}





@Composable
fun VideoItem(
    video: Video,
    onVideoSelected: (String) -> Unit,
    modifier: Modifier,
    smallSize: Boolean
) {
    if (smallSize) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onVideoSelected(video.videoUrl) }
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(video.thumbnailUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(100.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = video.title,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = video.description,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onVideoSelected(video.videoUrl) }
                .padding(8.dp)

        ) {
            Image(
                painter = rememberAsyncImagePainter(video.thumbnailUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .fillMaxWidth()

            )
            Text(
                text = video.title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
            val trimmedDescription = trimTextToWords(video.description, 5)
            ExandCards(title = trimmedDescription, content = video.description)
        }
    }
}

fun trimTextToWords(text: String, maxWords: Int): String {
    val words = text.split(" ")
    return if (words.size <= maxWords) {
        text
    } else {
        words.take(maxWords).joinToString(" ") + "..."
    }
}



@Composable
fun VideoList(
    videos: List<Video>,
    onVideoSelected: (String) -> Unit,
    modifier: Modifier,
    smallSize: Boolean
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(videos.size) { index ->
            val video = videos[index]
            VideoItem(video, onVideoSelected,modifier,smallSize)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewVideoItem() {
    val smallSize = true
    val mockVideo = Video(
        id = 1,
        videoUrl = "https://example.com/video.mp4",
        thumbnailUrl = "https://static1.srcdn.com/wordpress/wp-content/uploads/2024/07/sonic-the-hedgehog-3-sonic-and-shadow.jpg",
        title = "Sample Video Title",
        description = "This is a sample description for the video. It is intentionally long to test trimming."
    )

    Column {
        VideoItem(
            video = mockVideo,
            onVideoSelected = {},
            modifier = Modifier.height(100.dp),
            smallSize = smallSize
        )
        Spacer(modifier = Modifier.height(16.dp))
        VideoItem(
            video = mockVideo,
            onVideoSelected = {},
            modifier = Modifier.height(250.dp),
            smallSize = smallSize
        )
    }
}

@Serializable
object SupabaseVideoPlayer