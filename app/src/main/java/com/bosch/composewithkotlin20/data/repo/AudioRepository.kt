package com.bosch.composewithkotlin20.data.repo

import com.bosch.composewithkotlin20.data.model.data.Audio
import com.bosch.composewithkotlin20.util.service.mediaPlayer.ContentResolverHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AudioRepository(
	private val contentResolver: ContentResolverHelper,
) {
	suspend fun getAudioData(): List<Audio> = withContext(Dispatchers.IO) {
		contentResolver.getAudioData()
	}
}