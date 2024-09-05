package com.bosch.composewithkotlin20.di

import androidx.room.Room
import com.bosch.composewithkotlin20.data.model.database.AppDatabase
import org.koin.dsl.module

val  DatabaseModule = module {

    single {
        Room.databaseBuilder(get(), AppDatabase::class.java, "cafe_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppDatabase>().cafeDao() }

}