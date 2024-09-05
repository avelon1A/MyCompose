package com.bosch.composewithkotlin20.data.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bosch.composewithkotlin20.data.model.data.CafeEntity
import com.bosch.composewithkotlin20.domain.manger.CafeDao

@Database(entities = [CafeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cafeDao(): CafeDao
}
