package com.bosch.composewithkotlin20.util.service.mediaPlayer

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import androidx.media3.ui.PlayerNotificationManager
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.MainActivity

private const val NOTIFICATION_ID = 101
private const val NOTIFICATION_CHANNEL_NAME = "notification channel 1"
private const val NOTIFICATION_CHANNEL_ID = "notification channel id 1"

class AudioNotificationManager(
	private val context: Context,
	private val exoPlayer: ExoPlayer,
) {
	private val notificationManager: NotificationManagerCompat =
		NotificationManagerCompat.from(context)
	
	init {
		createNotificationChannel()
	}
	
	@UnstableApi
	fun startNotificationService(
		mediaSessionService: MediaSessionService,
		mediaSession: MediaSession,
	) {
		buildNotification(mediaSession)
		startForeGroundNotificationService(mediaSessionService)
	}
	
	private fun startForeGroundNotificationService(mediaSessionService: MediaSessionService) {
		val intent = Intent(context, MainActivity::class.java)
		intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
		
		val pendingIntent = PendingIntent.getActivity(
			context,
			0,
			intent,
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) PendingIntent.FLAG_IMMUTABLE else PendingIntent.FLAG_UPDATE_CURRENT
		)
		
		val notification = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
			.setCategory(NotificationCompat.CATEGORY_SERVICE)
			.setContentIntent(pendingIntent)
			.setSmallIcon(R.drawable.robot_android_svgrepo_com)
			.build()
		
		mediaSessionService.startForeground(NOTIFICATION_ID, notification)
	}
	
	@UnstableApi
	private fun buildNotification(mediaSession: MediaSession) {
		PlayerNotificationManager.Builder(
			context,
			NOTIFICATION_ID,
			NOTIFICATION_CHANNEL_ID
		)
			.setMediaDescriptionAdapter(
				AudioNotificationAdapter(
					context = context,
					pendingIntent = mediaSession.sessionActivity
				)
			)
			.setSmallIconResourceId(R.drawable.robot_android_svgrepo_com)
			.build()
			.also {
				it.setMediaSessionToken(mediaSession.sessionCompatToken)
				it.setUseFastForwardActionInCompactView(true)
				it.setUseRewindActionInCompactView(true)
				it.setUseNextActionInCompactView(true)
				it.setPriority(NotificationCompat.PRIORITY_LOW)
				it.setPlayer(exoPlayer)
			}
	}
	
	private fun createNotificationChannel() {
		val channel = NotificationChannel(
			NOTIFICATION_CHANNEL_ID,
			NOTIFICATION_CHANNEL_NAME,
			NotificationManager.IMPORTANCE_LOW
		)
		notificationManager.createNotificationChannel(channel)
	}
}
