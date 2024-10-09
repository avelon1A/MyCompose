package com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.data.model.response.LoginResponse
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.LoginViewModel
import com.bosch.composewithkotlin20.util.ApiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
	val keyboardController = LocalSoftwareKeyboardController.current
	val focusManager = LocalFocusManager.current
	val username by viewModel.username.collectAsState()
	val password by viewModel.password.collectAsState()
	val loginState by viewModel.loginState.collectAsState()
	val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black
	
	Surface() {
		Column(modifier = Modifier
			.fillMaxSize()
			.pointerInput(Unit) {
				detectTapGestures(onTap = { focusManager.clearFocus() })
			}
		) {
			TopSection()
			Spacer(modifier = Modifier.height(36.dp))
			Column(modifier = Modifier.padding(horizontal = 30.dp)) {
				TextFieldMy(uiColor,username,"email","enter your email", onValueChange = viewModel::onUsernameChanged)
				
				Spacer(modifier = Modifier.height(5.dp))
				TextFieldMy(uiColor,password,"password","enter your password", onValueChange = viewModel::onPasswordChanged)
				
				Button(
					onClick = {
						viewModel.login()
						keyboardController?.hide()
							  },
					modifier = Modifier
						.padding(top = 10.dp)
						.fillMaxWidth()
						.height(40.dp),
					colors = ButtonDefaults.buttonColors(
						containerColor = MaterialTheme.colorScheme.primaryContainer ,
						contentColor = Color.White,
						disabledContainerColor = Color.Gray,
						disabledContentColor = Color.White
					), shape = RoundedCornerShape(4.dp)
				) {
					
					when (loginState) {
						is ApiState.Loading -> {
							CircularProgressIndicator(
								modifier = Modifier
									.height(16.dp)
									.width(16.dp),
								strokeWidth = 2.dp,
								color = Color.White
							)
						}
						is ApiState.Success -> {
							Text((loginState as ApiState.Success<LoginResponse>).data.username)
						}
						
						is ApiState.Error -> {
							Text(
								"${(loginState as ApiState.Error).message}",
								color = Color.Red
							)
						}
						
						is ApiState.Initial -> {
							Text(text = "Login")
						}
					}
					
				}
			}
		}
		
	}
	
}

@Composable
fun TextFieldMy(uiColor: Color, initialValue:String, label:String, hint:String="", onValueChange: (String) -> Unit) {


	TextField(modifier = Modifier.fillMaxWidth(),
		value = initialValue, onValueChange =  onValueChange,
		label = { Text(text = label, color = uiColor) },
		placeholder = { Text(text = hint, color = uiColor) },
		colors = TextFieldDefaults.colors(
			focusedTextColor = uiColor,
			unfocusedTextColor = uiColor,

		)
	)
}

@Composable
private fun TopSection() {
	val uiColor = if (isSystemInDarkTheme()) Color.White else Color.Black
	val image = if (isSystemInDarkTheme())  R.drawable.nightnew else R.drawable.topnew
	Box( contentAlignment = Alignment.TopCenter) {
		Image(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(fraction = 0.46f),
			painter = painterResource(id =image),
			contentDescription = null,
			contentScale = ContentScale.FillBounds,
			
		)
		Row(
			modifier = Modifier.padding(top = 80.dp), verticalAlignment = Alignment.CenterVertically
		) {
			Icon(
				modifier = Modifier
					.height(42.dp)
					.width(42.dp),
				painter = painterResource(id = R.drawable.logo),
				contentDescription = "app logo"
			)
			Spacer(modifier = Modifier.width(15.dp))
			Column {
				Text(
					text = stringResource(id = R.string.login_title),
					style = MaterialTheme.typography.headlineMedium,
					color = uiColor
				)
				Text(
					text = stringResource(id = R.string.login_subtitle),
					style = MaterialTheme.typography.titleMedium,
					color = uiColor
				)
			}
		}
		Text(
			text = stringResource(id = R.string.login),
			modifier = Modifier.padding(top = 316.dp),
			style = MaterialTheme.typography.headlineMedium,
			color = uiColor
		)
		
	}
}


