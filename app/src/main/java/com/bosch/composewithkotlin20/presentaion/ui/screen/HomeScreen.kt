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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bosch.composewithkotlin20.R
import com.bosch.composewithkotlin20.presentaion.ui.common.AppBar
import com.bosch.composewithkotlin20.presentaion.ui.todo.TodoScreen
import kotlinx.serialization.Serializable

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
			AppBar()
		},
		content = { innerPadding ->
			ButtonGrid(buttonList, navController, innerPadding,Modifier)
		}
	)
}

@Composable
fun ButtonGrid(list: List<ButtonInfo>, navController: NavController, innerPadding: PaddingValues,modifier: Modifier) {
	LazyColumn(
		modifier = Modifier
			.fillMaxSize()
			.padding(innerPadding), 
		horizontalAlignment = Alignment.CenterHorizontally
	) {

			items(list) { buttonItem ->
				CustomButton(
					modifier = Modifier
						.fillMaxWidth(),
					text = buttonItem.title,
					onClick = { navController.navigate(buttonItem.route) }
				)
			}
		}
}

@Composable
fun CustomButton(modifier: Modifier,text: String, onClick: () -> Unit) {
	Box(
		modifier = Modifier
			.padding(5.dp)
			.shadow(
				elevation = 4.dp,
				shape = RoundedCornerShape(5.dp),
				clip = true
			)
			.clip(RoundedCornerShape(5.dp))
			.background(Color.Transparent)
	) {
		Box(
			modifier = Modifier
				.fillMaxWidth()
				.requiredHeight(50.dp)
				.clip(shape = RoundedCornerShape(5.dp))
				.background(color = Color.White)
				.border(
					border = BorderStroke(1.dp, Color(0xFFCECECE)),
					shape = RoundedCornerShape(5.dp)
				)
				.clickable(onClick = onClick),
			contentAlignment = Alignment.Center
		) {
			Text(text)
		}
	}
	
}

data class ButtonInfo(val title: String, val route: Any)


@Preview
@Composable
fun HomeScreenPreview() {
	val fakeNavController = fakeNavController()
	HomeScreen(navController = fakeNavController, modifier = Modifier)
}
@Composable
fun fakeNavController(): NavController {
	return rememberNavController()
}

@Serializable
object HomeScreen