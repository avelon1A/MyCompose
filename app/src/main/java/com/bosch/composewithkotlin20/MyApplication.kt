package com.bosch.composewithkotlin20

import android.app.Application
import com.bosch.composewithkotlin20.di.DatabaseModule
import com.bosch.composewithkotlin20.di.appModule
import com.bosch.composewithkotlin20.presentaion.ui.todo.di.TodoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
	override fun onCreate() {
		super.onCreate()
		startKoin {
			androidLogger(Level.ERROR)
			androidContext(this@MyApplication)
			modules(appModule,TodoModule,DatabaseModule)
		}
	}
}