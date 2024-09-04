package com.bosch.composewithkotlin20.data.model.data

import kotlinx.serialization.Serializable


@Serializable
data class Video(
    val id: Int,
    val title: String,
    val description: String,
    val thumbnailUrl: String,
    val videoUrl: String
)