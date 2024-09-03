//package com.bosch.composewithkotlin20.presentaion.ui.screen.supabase
//
//import androidx.arch.core.executor.testing.InstantTaskExecutorRule
//import androidx.lifecycle.Observer
//import com.bosch.composewithkotlin20.data.model.data.Video
//import io.github.jan.supabase.SupabaseClient
//import io.github.jan.supabase.postgrest.from
//import io.github.jan.supabase.postgrest.query.Columns
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//import kotlinx.coroutines.flow.flowOf
//import kotlinx.coroutines.test.StandardTestDispatcher
//import kotlinx.coroutines.test.TestScope
//import kotlinx.coroutines.test.runTest
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.mockito.Mockito.*
//
//class SupabaseVideoPlayerViewModelTest {
//
//    @get:Rule
//    val instantExecutorRule = InstantTaskExecutorRule()
//
//    private lateinit var supabaseClient: SupabaseClient
//    private lateinit var viewModel: SupabaseVideoPlayerViewModel
//    private lateinit var observer: Observer<List<Video>>
//    private lateinit var selectedVideoObserver: Observer<String>
//
//    private val testDispatcher = StandardTestDispatcher()
//    private val testScope = TestScope(testDispatcher)
//
//    @Before
//    fun setup() {
//        supabaseClient = mock(SupabaseClient::class.java)
//        viewModel = SupabaseVideoPlayerViewModel(supabaseClient)
//        observer = mock(Observer::class.java) as Observer<List<Video>>
//        selectedVideoObserver = mock(Observer::class.java) as Observer<String>
//    }
//
//    @Test
//    fun `fetchVideos should update videos LiveData`() = testScope.runTest {
//        // Arrange
//        val videoList = listOf(Video(id = "1", title = "Sample Video"))
//        `when`(supabaseClient.from("video").select(Columns.ALL).decodeList<Video>()).thenReturn(videoList)
//
//        // Act
//        viewModel.videos.observeForever(observer)
//        viewModel.fetchVideos()
//
//        // Assert
//        verify(observer).onChanged(videoList)
//    }
//
//    @Test
//    fun `onVideoSelected should update selectedVideoUrl LiveData`() = testScope.runTest {
//        // Arrange
//        val videoUrl = "http://example.com/video.mp4"
//
//        // Act
//        viewModel.selectedVideoUrl.observeForever(selectedVideoObserver)
//        viewModel.onVideoSelected(videoUrl)
//
//        // Assert
//        verify(selectedVideoObserver).onChanged(videoUrl)
//    }
//
//    @Test
//    fun `updatePlaybackPosition should update currentPlaybackPosition`() {
//        // Arrange
//        val newPosition = 5000L
//
//        // Act
//        viewModel.updatePlaybackPosition(newPosition)
//
//        // Assert
//        assert(viewModel.currentPlaybackPosition == newPosition)
//    }
//
//    @Test
//    fun `observeRealtimeVideoUpdates should collect video list and update videos LiveData`() = testScope.runTest {
//        // Arrange
//        val videoList = listOf(Video(id = "1", title = "Sample Video"))
//        `when`(supabaseClient.from("video").selectAsFlow(Video::id)).thenReturn(flowOf(videoList))
//
//        // Act
//        viewModel.videos.observeForever(observer)
//        viewModel.observeRealtimeVideoUpdates()
//
//        // Assert
//        verify(observer).onChanged(videoList)
//    }
//}
