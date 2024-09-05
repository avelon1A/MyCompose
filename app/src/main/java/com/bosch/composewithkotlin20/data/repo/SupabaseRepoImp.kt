package com.bosch.composewithkotlin20.data.repo

import com.bosch.composewithkotlin20.data.model.data.Cafe
import com.bosch.composewithkotlin20.data.model.data.Video
import com.bosch.composewithkotlin20.data.model.data.toDomain
import com.bosch.composewithkotlin20.data.model.data.toEntity
import com.bosch.composewithkotlin20.domain.repo.SupabaseRepository
import com.bosch.composewithkotlin20.domain.manger.CafeDao
import com.bosch.composewithkotlin20.util.NetworkHelper
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class SupabaseRepoImp(
     private val supabaseClient: SupabaseClient,
     private val cafeDao: CafeDao,
     private val networkHelper: NetworkHelper
 ) : SupabaseRepository {
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

    override suspend fun fetchCafes(): Flow<List<Cafe>> {
        return flow {
            if (networkHelper.isOnline()) {
                try {
                    val apiCafeList = supabaseClient.from("cafe").select().decodeList<Cafe>()

                    emit(apiCafeList)

                    cafeDao.deleteAllCafes()
                    cafeDao.insertCafes(apiCafeList.map { it.toEntity() })

                } catch (e: Exception) {
                    emit(emptyList())
                }
            } else {
                val cachedCafes = cafeDao.getAllCafes().map { entities ->
                    entities.map { it.toDomain() }
                }
                emitAll(cachedCafes)
            }
        }
    }



    @OptIn(SupabaseExperimental::class)
    override suspend fun observeRealtimeCafes(): Flow<List<Cafe>> {
       return flow {
           if (networkHelper.isOnline()) {
               try {
                   supabaseClient.from("cafe")
                       .selectAsFlow(Cafe::id)
                       .collect { updatedCafe ->
                           emit(updatedCafe)
                           cafeDao.deleteAllCafes()
                           cafeDao.insertCafes(updatedCafe.map { it.toEntity() })

                       }
               } catch (e: Exception) {
                   emit(emptyList())
               }
           }
           else {
               val cachedCafes = cafeDao.getAllCafes().map { entities ->
                   entities.map { it.toDomain() }
               }
               emitAll(cachedCafes)
           }
       }

    }
}