package com.bosch.composewithkotlin20.presentaion.ui.screen.supabase

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosch.composewithkotlin20.data.model.data.Cafe
import com.bosch.composewithkotlin20.data.model.data.Video
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SupabaseVideoPlayerViewModel(private val supabaseClient: SupabaseClient): ViewModel() {
    private val _videos = MutableStateFlow<List<Video>>(emptyList())
    val videos: StateFlow<List<Video>> = _videos

    var selectedVideoUrl: String? by mutableStateOf(null)
        private set

    var currentPlaybackPosition: Long  by mutableLongStateOf( 0L)
        private set

    init {
        fetchVideos()
        observeRealtimeVideoUpdates()
    }

    @OptIn(SupabaseExperimental::class)
    private fun observeRealtimeVideoUpdates() {
        viewModelScope.launch {
            try {
                supabaseClient.from("video")
                    .selectAsFlow(Video::id)
                    .collect { videoList ->
                        _videos.value = videoList

                    }
            } catch (e: Exception) {
                Log.d("SupabaseVideoPlayerViewModel", "Error observing realtime updates: ${e.message}")
            }
        }
    }
    private fun fetchVideos() {
        viewModelScope.launch {
            try {
                val videoList = supabaseClient.from("video")
                    .select(Columns.ALL).decodeList<Video>()
                _videos.value = videoList
                Log.d("video player", "$videoList")
            } catch (e: Exception) {
                Log.d("SupabaseVideoPlayerViewModel", "Error observing  updates: ${e.message}")
            }
        }
        }



    fun onVideoSelected(url: String) {
        selectedVideoUrl = url
    }

    fun updatePlaybackPosition(position: Long) {
        currentPlaybackPosition = position
    }
}
