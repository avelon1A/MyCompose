package com.bosch.composewithkotlin20.data.api

import com.bosch.composewithkotlin20.data.model.request.LoginRequest
import com.bosch.composewithkotlin20.data.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

fun interface ApiService {
	@POST("auth/login")
	@Headers("Content-Type: application/json")
	suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}



