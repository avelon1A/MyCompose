package com.bosch.composewithkotlin20.presentaion.ui.screen.supabase

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosch.composewithkotlin20.data.model.data.Video
import com.bosch.composewithkotlin20.domain.repo.SupabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SupabaseVideoPlayerViewModel(private val supabaseRepository: SupabaseRepository): ViewModel() {
    private val _videos = MutableStateFlow<List<Video>>(emptyList())
    val videos: StateFlow<List<Video>> = _videos

    private var _selectedVideoUrl = MutableLiveData<String>(null)
    val selectedVideoUrl: LiveData<String> = _selectedVideoUrl


    var currentPlaybackPosition: Long  by mutableLongStateOf( 0L)
        private set

    init {
        fetchVideos()
        observeRealtimeVideoUpdates()
    }

    private fun observeRealtimeVideoUpdates() {
        viewModelScope.launch {
            supabaseRepository.fetchVideos().let { videoList ->
                _videos.value = videoList

            }
        }
    }
    private fun fetchVideos() {
        viewModelScope.launch {
            supabaseRepository.observeRealtimeVideos().collect { videoList ->
                _videos.value = videoList
            }
        }
    }



    fun onVideoSelected(url: String) {
        _selectedVideoUrl.value = url
    }

    fun updatePlaybackPosition(position: Long) {
        currentPlaybackPosition = position
    }
}
