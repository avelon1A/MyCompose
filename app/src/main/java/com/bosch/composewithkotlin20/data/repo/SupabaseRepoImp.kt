package com.bosch.composewithkotlin20.data.repo

import com.bosch.composewithkotlin20.data.model.data.Cafe
import com.bosch.composewithkotlin20.data.model.data.Video
import com.bosch.composewithkotlin20.domain.Repo.SupabaseRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

 class SupabaseRepoImp(private val supabaseClient: SupabaseClient) : SupabaseRepository {
    override suspend fun fetchVideos(): List<Video> {
        return try {
            supabaseClient.from("video").select(Columns.ALL).decodeList<Video>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    @OptIn(SupabaseExperimental::class)
    override suspend fun observeRealtimeVideos(): Flow<List<Video>> {
        return flow {
            try {
               supabaseClient.from("video").selectAsFlow(Video::id).collect{ videoList ->
                    emit(videoList)
                }
            } catch (e: Exception) {
                emit(emptyList())
            }

        }

    }

    override suspend fun fetchCafes(): List<Cafe> {
        return try {
            supabaseClient.from("cafe").select().decodeList<Cafe>()
        } catch (e: Exception) {
            emptyList()
        }
    }

    @OptIn(SupabaseExperimental::class)
    override suspend fun observeRealtimeCafes(): Flow<List<Cafe>> {
       return flow {
           try {
               supabaseClient.from("cafe")
                   .selectAsFlow(Cafe::id)
                   .collect { updatedCafe ->
                        emit(updatedCafe)

                   }
           } catch (e: Exception) {
               emit(emptyList())
           }
       }

    }
}