package com.bosch.composewithkotlin20.data.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bosch.composewithkotlin20.data.api.ApiService
import com.bosch.composewithkotlin20.data.model.request.LoginRequest
import com.bosch.composewithkotlin20.data.model.response.LoginResponse
import com.bosch.composewithkotlin20.util.ApiState
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.doThrow
import retrofit2.Response
import kotlin.test.assertEquals

class LoginRepositoryTest {
	
	@get:Rule
	val instantTaskExecutorRule = InstantTaskExecutorRule()
	
	private val apiService: ApiService = mock(ApiService::class.java)
	private val repository = LoginRepository(apiService)
	
	private val testDispatcher = StandardTestDispatcher()
	
	@Test
	fun `test login success`() = runTest(testDispatcher) {
		val loginRequest = LoginRequest("testUser", "testPass")
		val loginResponse = LoginResponse(
			id = 1,
			username = "testUser",
			email = "test@example.com",
			firstName = "Test",
			lastName = "User",
			gender = "Male",
			image = "image_url",
			token = "token123",
			refreshToken = "refreshToken123"
		)
		val responseBody = Response.success(loginResponse)
		
		`when`(apiService.login(loginRequest)).thenReturn(responseBody)
		
		val result = repository.login("testUser", "testPass")
		
		assertEquals(ApiState.Success(loginResponse), result)
	}
	
	@Test
	fun `test login failure with error response`() = runTest(testDispatcher) {
		val loginRequest = LoginRequest("testUser", "testPass")
		val errorMessage = "Login failed"
		val responseBody = Response.error<LoginResponse>(401, errorMessage.toResponseBody())
		
		`when`(apiService.login(loginRequest)).thenReturn(responseBody)
		
		val result = repository.login("testUser", "testPass")
		
		assertEquals(ApiState.Error(errorMessage), result)
	}
	
	@Test
	fun `test login failure with exception`() = runTest(testDispatcher) {
		val loginRequest = LoginRequest("testUser", "testPass")
		val runtimeException = RuntimeException("Network error")
		
		doThrow(runtimeException).`when`(apiService).login(loginRequest)
		
		val result = repository.login("testUser", "testPass")
		assertEquals(ApiState.Error(throwable = runtimeException), result)
	}
	
	@Test
	fun `test login failure with empty response body`() = runTest(testDispatcher) {
		val loginRequest = LoginRequest("testUser", "testPass")
		val responseBody = Response.success<LoginResponse>(null)
		
		`when`(apiService.login(loginRequest)).thenReturn(responseBody)
		
		val result = repository.login("testUser", "testPass")
		
		assertEquals(ApiState.Error("Empty response body"), result)
	}
}
