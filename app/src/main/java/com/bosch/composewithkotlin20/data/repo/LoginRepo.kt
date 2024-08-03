package com.bosch.composewithkotlin20.data.repo

import com.bosch.composewithkotlin20.data.api.ApiService
import com.bosch.composewithkotlin20.data.model.request.LoginRequest
import com.bosch.composewithkotlin20.data.model.response.LoginResponse
import com.bosch.composewithkotlin20.util.ApiState

class LoginRepository(
	private val apiService: ApiService
) {
	suspend fun login(username: String, password: String): ApiState<LoginResponse> {
		val request = LoginRequest(username, password)
		return try {
			val response = apiService.login(request)
			if (response.isSuccessful) {
				val body = response.body()
				if (body != null) {
					ApiState.Success(body)
				} else {
					ApiState.Error("Empty response body")
				}
			} else {
				val errorBody = response.errorBody()?.string()
				ApiState.Error(errorBody ?: "Unknown error")
			}
		} catch (e: Exception) {
			ApiState.Error(throwable = e)
		}
	}
}
