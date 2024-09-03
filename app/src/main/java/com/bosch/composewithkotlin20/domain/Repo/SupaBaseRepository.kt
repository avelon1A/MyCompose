package com.bosch.composewithkotlin20.domain.Repo


import com.bosch.composewithkotlin20.data.model.data.Cafe
import com.bosch.composewithkotlin20.data.model.data.Video
import kotlinx.coroutines.flow.Flow

interface SupabaseRepository {
    suspend fun fetchVideos(): List<Video>
    suspend fun observeRealtimeVideos(): Flow<List<Video>>

    suspend fun fetchCafes(): List<Cafe>
    suspend fun observeRealtimeCafes(): Flow<List<Cafe>>
}
