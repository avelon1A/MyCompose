package com.bosch.composewithkotlin20.data.model.data

import kotlinx.serialization.Serializable

@Serializable
data class Cafe(
    val id:String,
    val name:String,
    val image:String,
)
