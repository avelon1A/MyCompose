package com.bosch.composewithkotlin20.data.model.response

data class LoginResponse(
	val id: Int,
	val username: String,
	val email: String,
	val firstName: String,
	val lastName: String,
	val gender: String,
	val image: String,
	val token: String,
	val refreshToken: String
)