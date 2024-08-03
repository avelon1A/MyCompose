package com.bosch.composewithkotlin20.presentaion.ui.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bosch.composewithkotlin20.data.model.response.LoginResponse
import com.bosch.composewithkotlin20.data.repo.LoginRepository
import com.bosch.composewithkotlin20.util.ApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class LoginViewModelTest {
	
	@get:Rule
	val instantExecutorRule = InstantTaskExecutorRule()
	
	private val repository: LoginRepository = mock()
	private lateinit var viewModel: LoginViewModel
	
	private val testDispatcher = UnconfinedTestDispatcher()
	
	@Before
	fun setUp() {
		Dispatchers.setMain(testDispatcher)
		viewModel = LoginViewModel(repository)
	}
	
	@After
	fun tearDown() {
		Dispatchers.resetMain()
	}
	
	@Test
	fun `onUsernameChanged updates username`() {
		val newUsername = "testUser"
		viewModel.onUsernameChanged(newUsername)
		assertEquals(newUsername, viewModel.username.value)
	}
	
	@Test
	fun `onPasswordChanged updates password`() {
		val newPassword = "testPass"
		viewModel.onPasswordChanged(newPassword)
		assertEquals(newPassword, viewModel.password.value)
	}
	
	@Test
	fun `login sets loginState to Loading and then to Success`() = runTest(UnconfinedTestDispatcher()) {
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
		whenever(repository.login("testUser", "testPass")).thenReturn(ApiState.Success(loginResponse))
		
		viewModel.onUsernameChanged("testUser")
		viewModel.onPasswordChanged("testPass")
		
		val states = mutableListOf<ApiState<LoginResponse>>()
		
		val stateCollectorJob = launch {
			viewModel.loginState
				.take(3)
				.collect { state ->
					states.add(state)
				}
		}
		
		viewModel.login()
		
		advanceUntilIdle()
		stateCollectorJob.cancel()
		
		println("Collected states: $states")
		assertTrue(states.contains(ApiState.Loading))
		assertTrue(states.contains(ApiState.Success(loginResponse)))
		
		val loadingIndex = states.indexOf(ApiState.Loading)
		val errorIndex = states.indexOf(ApiState.Success(loginResponse))
		assertTrue(loadingIndex < errorIndex)
	}
	
	
	
	@Test
	fun `login sets loginState to Loading and then to Error`() = runTest(UnconfinedTestDispatcher()) {
		val errorMessage = "Login failed"
		whenever(repository.login("testUser", "testPass")).thenReturn(ApiState.Error(errorMessage))
		
		viewModel.onUsernameChanged("testUser")
		viewModel.onPasswordChanged("testPass")
		
		assertEquals(ApiState.Initial, viewModel.loginState.value)
	
		val states = mutableListOf<ApiState<LoginResponse>>()
		
		val stateCollectorJob = launch {
			viewModel.loginState
				.take(3)
				.collect { state ->
					states.add(state)
				}
		}
		
		viewModel.login()
		
		advanceUntilIdle()
		stateCollectorJob.cancel()
		
		println("Collected states: $states")
		assertTrue(states.contains(ApiState.Loading))
		assertTrue(states.contains(ApiState.Error(errorMessage)))
		
		val loadingIndex = states.indexOf(ApiState.Loading)
		val errorIndex = states.indexOf(ApiState.Error(errorMessage))
		assertTrue(loadingIndex < errorIndex)
	}
}
