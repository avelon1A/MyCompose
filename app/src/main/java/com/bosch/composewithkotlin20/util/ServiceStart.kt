package com.bosch.composewithkotlin20.util

import com.bosch.composewithkotlin20.util.service.mediaPlayer.AudioService
import android.content.Context
import android.content.Intent

class ServiceStarter(
 private val context: Context
) {
	fun startService() {
		val intent = Intent(context, AudioService::class.java)
		context.startForegroundService(intent)
	}
}