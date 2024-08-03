package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoScreen
import kotlinx.serialization.Serializable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController, modifier: Modifier) {
	val buttonList = listOf(
		ButtonInfo("first ", ScreenB),
		ButtonInfo("expand text", ScreenC),
		ButtonInfo("Box layout", ThirdScreen),
		ButtonInfo("Text", TextScreen),
		ButtonInfo("Text Selection", TextSelectable),
		ButtonInfo("TextField", Textfield),
		ButtonInfo("google btn", GoogleButtonScreen),
		ButtonInfo("coil screen", CoilScreen),
		ButtonInfo("gradiant screen", GradientScreen),
		ButtonInfo("camera", CameraScreen),
		ButtonInfo("login screen", LoginScreen),
		ButtonInfo("circular indicator", CircularIndicatorScreen),
		ButtonInfo("OnBoarding", OnBoardingScreen),
		ButtonInfo("Bottom Navigation", BottomNavigationBar),
		ButtonInfo("Music Screen", MusicScreenContent),
		ButtonInfo("seekBar", Seekbar),
		ButtonInfo("snackbar", Snackbar),
		ButtonInfo("todo", TodoScreen),
		ButtonInfo("DropDown", DropDown),
		
		)
	
	Scaffold(
		topBar = {
			Box(
				modifier = Modifier
					.fillMaxWidth()
					.shadow(8.dp)
			) {
				
				TopAppBar(
					title = {},
					navigationIcon = {
						IconButton(onClick = { }) {
							Image(
								painter = painterResource(id = R.drawable.__icon__hamburger_button_),
								contentDescription = "Localized description"
							)
						}
					},
					actions = {
						IconButton(onClick = { /* doSomething() */ }) {
							Image(
								painter = painterResource(id = R.drawable.__icon__me_),
								contentDescription = "Localized description"
							)
						}
						
					},
				)
				
			}
		},
		content = { innerPadding ->
			ButtonGrid(buttonList, navController, innerPadding)
		}
	)
}

@Composable
fun ButtonGrid(list: List<ButtonInfo>, navController: NavController, innerPadding: PaddingValues) {
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			.padding(innerPadding),
		verticalArrangement = Arrangement.spacedBy(20.dp),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		items(list.chunked(2), key = { rowButtons ->
			rowButtons.hashCode()
		}) { rowButtons ->
			
			Row(
				modifier = Modifier.fillMaxWidth(),
				horizontalArrangement = Arrangement.SpaceAround,
				verticalAlignment = Alignment.CenterVertically
			) {
				rowButtons.forEach {

					CustomButton(modifier = Modifier, text = it.title, onClick = { navController.navigate(it.route) })
				}
			}
		}
		
		
	}
	
}

@Composable
fun CustomButton(modifier: Modifier,text: String, onClick: () -> Unit) {
	Box(
		modifier = Modifier
			.padding(10.dp)
			.shadow(
				elevation = 8.dp,
				shape = RoundedCornerShape(10.dp),
				clip = true
			)
			.clip(RoundedCornerShape(10.dp))
			.background(Color.Transparent)
	) {
		Box(
			modifier = Modifier
				.requiredWidth(119.dp)
				.requiredHeight(97.dp)
				.clip(shape = RoundedCornerShape(10.dp))
				.background(color = Color.White)
				.border(
					border = BorderStroke(1.dp, Color(0xFFCECECE)),
					shape = RoundedCornerShape(10.dp)
				)
				.clickable(onClick = onClick),
			contentAlignment = Alignment.Center
		) {
			Text(text)
		}
	}
	
}

data class ButtonInfo(val title: String, val route: Any)

@Serializable
object HomeScreen