package com.bosch.composewithkotlin20.data.model.data

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Audio(
	val uri: Uri?,
	val displayName: String,
	val id: Long,
	val artist: String,
	val data: String,
	val duration: Int,
	val title: String
) : Parcelable