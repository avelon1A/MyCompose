package com.bosch.composewithkotlin20.presentaion.ui.screen
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.data.model.response.LoginResponse
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.LoginViewModel
import com.bosch.composewithkotlin20.util.ApiState
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen1(viewModel: LoginViewModel = koinViewModel()) {
	
	val keyboardController = LocalSoftwareKeyboardController.current
	val focusManager = LocalFocusManager.current
	val username by viewModel.username.collectAsState()
	val password by viewModel.password.collectAsState()
	val loginState by viewModel.loginState.collectAsState()
	
	Column(
		modifier = Modifier.fillMaxSize().padding(16.dp)
			.pointerInput(Unit){
				detectTapGestures(onTap = {focusManager.clearFocus()})
			},
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		
		OutlinedTextField(
			value = username,
			onValueChange = { viewModel.onUsernameChanged(it) },
			label = { Text("Username") },
			modifier = Modifier.fillMaxWidth()
		)
		
		Spacer(modifier = Modifier.height(8.dp))
		
		OutlinedTextField(
			value = password,
			onValueChange = { viewModel.onPasswordChanged(it) },
			label = { Text("Password") },
			visualTransformation = PasswordVisualTransformation(),
			modifier = Modifier.fillMaxWidth()
		)
		
		Spacer(modifier = Modifier.height(16.dp))
		
		Button(onClick = {
			viewModel.login()
			keyboardController?.hide()
		}) {
			Text("Login")
		}
		
		Spacer(modifier = Modifier.height(16.dp))
		
		when (loginState) {
			is ApiState.Loading -> {
				CircularProgressIndicator()
			}
			is ApiState.Success -> {
				Text("Login Successful: ${(loginState as ApiState.Success<LoginResponse>).data.username}")
			}
		
			is ApiState.Error -> {
				Text("${(loginState as ApiState.Error).message}")
			}
			
			is ApiState.Initial -> {
				Text("")
			}
			}
		}
	}




@Serializable
object LoginScreen

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
	LoginScreen()
}
