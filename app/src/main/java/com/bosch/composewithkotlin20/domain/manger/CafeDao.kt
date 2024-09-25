package com.bosch.composewithkotlin20.domain.manger

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bosch.composewithkotlin20.data.model.data.CafeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CafeDao {
    @Query("SELECT * FROM cafes")
    fun getAllCafes(): Flow<List<CafeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCafes(cafes: List<CafeEntity>)

    @Query("DELETE FROM cafes")
    suspend fun deleteAllCafes()
}
