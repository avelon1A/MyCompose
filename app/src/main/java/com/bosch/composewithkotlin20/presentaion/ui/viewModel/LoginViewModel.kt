package com.bosch.composewithkotlin20.presentaion.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosch.composewithkotlin20.data.model.response.LoginResponse
import com.bosch.composewithkotlin20.data.repo.LoginRepository
import com.bosch.composewithkotlin20.util.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
	private val repository: LoginRepository
) : ViewModel() {
	
	private val _username = MutableStateFlow("")
	private val _password = MutableStateFlow("")
	
	val username: StateFlow<String> get() = _username
	val password: StateFlow<String> get() = _password
	
	private val _loginState = MutableStateFlow<ApiState<LoginResponse>>(ApiState.Initial)
	val loginState: StateFlow<ApiState<LoginResponse>> = _loginState
	
	fun onUsernameChanged(newUsername: String) {
		_username.value = newUsername
	}
	
	fun onPasswordChanged(newPassword: String) {
		_password.value = newPassword
	}
	
	fun login() {
		_loginState.value = ApiState.Loading
		viewModelScope.launch {
			val result = repository.login(username.value, password.value)
			_loginState.value = result
		}
	}
}

