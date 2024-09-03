package com.bosch.composewithkotlin20.presentaion.ui.screen.supabase

import android.app.Activity
import android.content.Context
import android.graphics.SurfaceTexture
import android.util.Log
import android.view.Surface
import android.view.TextureView
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.data.model.data.Video
import com.bosch.composewithkotlin20.presentaion.ui.common.AppBar
import com.bosch.composewithkotlin20.presentaion.ui.screen.ExandCards
import kotlinx.serialization.Serializable


@Composable
fun SupabaseVideoPlayer(
    navController: NavHostController, supabaseVideoPlayerViewModel: SupabaseVideoPlayerViewModel
) {
    val videos by supabaseVideoPlayerViewModel.videos.collectAsState()
    val smallSize by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    val selectedVideoUrl by supabaseVideoPlayerViewModel.selectedVideoUrl.observeAsState()


    val configuration = LocalConfiguration.current

    when (configuration.orientation  ) {
        android.content.res.Configuration.ORIENTATION_LANDSCAPE -> {
            if(supabaseVideoPlayerViewModel.selectedVideoUrl != null){

                Column(
                    modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    VideoPlayer(selectedVideoUrl!!,
                        onPlayingStateChanged = { playing ->
                            isPlaying = playing }, viewModel = supabaseVideoPlayerViewModel,modifier = Modifier.fillMaxSize())
                }
            }

        }
        android.content.res.Configuration.ORIENTATION_PORTRAIT -> {
            Scaffold(modifier = Modifier.background(MaterialTheme.colorScheme.surface), topBar = {
                AppBar(R.drawable.arrow_back, navController)
            }, content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    if (selectedVideoUrl == null) {
                        VideoList(videos = videos, onVideoSelected = { url ->
                            supabaseVideoPlayerViewModel.onVideoSelected(url)
                        }, modifier = Modifier.height(250.dp), smallSize = smallSize)
                    } else {
                        VideoPlayer(
                            selectedVideoUrl!!,
                            onPlayingStateChanged = { playing ->
                                isPlaying = playing
                            },
                            viewModel = supabaseVideoPlayerViewModel,
                            Modifier.height(250.dp)
                        )
                        VideoList(
                            videos = videos, onVideoSelected = { url ->
                                supabaseVideoPlayerViewModel.onVideoSelected(url)
                            }, modifier = Modifier.height(100.dp), smallSize = true
                        )
                    }
                }
            })

        }
        else -> {

        }
    }


}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(
    url: String,
    onPlayingStateChanged: (Boolean) -> Unit,
    viewModel: SupabaseVideoPlayerViewModel,
    modifier: Modifier = Modifier.fillMaxSize()
) {

    val context = LocalContext.current
    val view = LocalView.current

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(MediaItem.fromUri(url))
            prepare()
            seekTo(viewModel.currentPlaybackPosition)
            playWhenReady = true
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
                        onPlayingStateChanged(false)
                    }

                    Player.STATE_BUFFERING -> {
                        onPlayingStateChanged(exoPlayer.isLoading
                        )
                    }

                    Player.STATE_IDLE -> {
                        TODO()
                    }
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                onPlayingStateChanged(isPlaying)
            }
        }

        exoPlayer.addListener(listener)

        onDispose {
            viewModel.updatePlaybackPosition(exoPlayer.currentPosition) // Save the current position
            exoPlayer.removeListener(listener)
            exoPlayer.release()
        }
    }
    LaunchedEffect(view) {
        val window = (context as Activity).window
        window.insetsController?.let { controller ->
            controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
            controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_DEFAULT
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            val window = (context as Activity).window
            window.insetsController?.show(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
        }
    }

    AndroidView(
        factory = { context ->
            PlayerView(context).apply {
                setPlayer(exoPlayer)
                useController = true
                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT

            }
        },
        modifier = modifier,
        update = { view ->
            val player = view.player
            player?.setMediaItem(MediaItem.fromUri(url))
            player?.prepare()
            player?.playWhenReady = true
        }
    )

}



class CroppedTextureView(context: Context) : TextureView(context) {

    private var exoPlayer: ExoPlayer? = null

    init {
        surfaceTextureListener = object : SurfaceTextureListener {
            override fun onSurfaceTextureAvailable(surface: SurfaceTexture, width: Int, height: Int) {
                exoPlayer?.setVideoSurface(Surface(surface))
                requestLayout()
            }

            override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture, width: Int, height: Int) {
                // Handle size changes if needed
            }

            override fun onSurfaceTextureDestroyed(surface: SurfaceTexture): Boolean {
                exoPlayer?.clearVideoSurface()
                return true
            }

            override fun onSurfaceTextureUpdated(surface: SurfaceTexture) {
                // Handle updates if needed
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = MeasureSpec.getSize(heightMeasureSpec)
        setMeasuredDimension(width, height)
    }
}



@Composable
fun VideoItem(
    video: Video, onVideoSelected: (String) -> Unit, modifier: Modifier, smallSize: Boolean
) {
    if (smallSize) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .clickable { onVideoSelected(video.videoUrl) }
            .padding(8.dp)) {
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
                    text = video.description, style = MaterialTheme.typography.bodySmall
                )
            }
        }
    } else {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable { onVideoSelected(video.videoUrl) }
            .padding(8.dp)

        ) {
            Image(
                painter = rememberAsyncImagePainter(video.thumbnailUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = modifier.fillMaxWidth()

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
    videos: List<Video>, onVideoSelected: (String) -> Unit, modifier: Modifier, smallSize: Boolean
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(videos.size) { index ->
            val video = videos[index]
            VideoItem(video, onVideoSelected, modifier, smallSize)
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