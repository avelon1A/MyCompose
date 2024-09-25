package com.bosch.composewithkotlin20.util.service.mediaPlayer
import android.content.Intent
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.session.MediaSession
import androidx.media3.session.MediaSessionService
import org.koin.android.ext.android.inject

class AudioService : MediaSessionService() {
	
	private val mediaSession: MediaSession by inject()
	private val notificationManager: AudioNotificationManager by inject()
	
	@UnstableApi
	override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
		notificationManager.startNotificationService(
			mediaSession = mediaSession,
			mediaSessionService = this
		)
		return super.onStartCommand(intent, flags, startId)
	}
	
	override fun onGetSession(controllerInfo: MediaSession.ControllerInfo): MediaSession =
		mediaSession
	
	override fun onDestroy() {
		super.onDestroy()
		mediaSession.apply {
			release()
			if (player.playbackState != Player.STATE_IDLE) {
				player.seekTo(0)
				player.playWhenReady = false
				player.stop()
			}
		}
	}
}
