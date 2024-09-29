package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.data.model.data.Page
import com.bosch.composewithkotlin20.data.model.data.pages
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.event.OnBoardingEvent
import com.bosch.composewithkotlin20.util.Const.MediumPadding2
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

@Composable
fun OnBoardingScreen(
	event: (OnBoardingEvent) -> Unit
) {
	Column(modifier = Modifier.fillMaxSize()) {
		val pagerState = rememberPagerState(initialPage = 0) {
			pages.size
		}
		val buttonsState = remember {
			derivedStateOf {
				when (pagerState.currentPage) {
					0 -> listOf("", "Next")
					1 -> listOf("Back", "Next")
					2 -> listOf("Back", "Get Started")
					else -> listOf("", "")
				}
			}
		}
		HorizontalPager(state = pagerState) { index ->
			OnBoarding(page = pages[index], modifier = Modifier)
		}
		Spacer(modifier = Modifier.weight(1f))
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(horizontal = MediumPadding2)
				.navigationBarsPadding(),
			horizontalArrangement = Arrangement.SpaceBetween,
			verticalAlignment = Alignment.CenterVertically
		) {
			PageIndicator(
				modifier = Modifier,
				pageSize = pages.size,
				selectedPage = pagerState.currentPage
			)
			
			Row(verticalAlignment = Alignment.CenterVertically) {
				val scope = rememberCoroutineScope()
				if (buttonsState.value[0].isNotEmpty()) {
					NewsTextButton(
						text = buttonsState.value[0],
						onClick = {
							scope.launch {
								pagerState.animateScrollToPage(
									page = pagerState.currentPage - 1
								)
							}
							
						}
					)
				}
				NewsButton(
					text = buttonsState.value[1],
					onClick = {
						scope.launch {
							if (pagerState.currentPage == 2){
								event(OnBoardingEvent.SaveAtEntryPoint)
							}else{
								pagerState.animateScrollToPage(
									page = pagerState.currentPage + 1
								)
							}
						}
					}
				)
			}
		}
		Spacer(modifier = Modifier.weight(0.5f))
	}
}
@Composable
fun OnBoarding(
	modifier: Modifier = Modifier,
	page: Page
) {
	Column(modifier = modifier.background(MaterialTheme.colorScheme.background)) {
		Image(
			modifier = Modifier
				.fillMaxWidth()
				.fillMaxHeight(0.60f),
			painter = painterResource(id = page.image),
			contentDescription = null,
			contentScale = ContentScale.Crop
		)
		Spacer(modifier = Modifier.height(16.dp))
		Text(
			text = page.title,color = MaterialTheme.colorScheme.scrim,
			style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
			modifier = Modifier.padding(horizontal = 16.dp)
		)
		Spacer(modifier = Modifier.height(21.dp))
		Text(
			text = page.content,color = MaterialTheme.colorScheme.scrim,
			style = MaterialTheme.typography.bodyMedium,
			modifier = Modifier.padding(horizontal = 21.dp)
		)
	}
	
}
@Composable
fun PageIndicator(
	modifier: Modifier = Modifier,
	pageSize: Int,
	selectedPage: Int,
	selectedColor: Color = MaterialTheme.colorScheme.primary,
	unselectedColor: Color = Color.Gray
) {
	Row(
		horizontalArrangement = Arrangement.spacedBy(5.dp)
	) {
		repeat(pageSize) {
			Box(
				modifier = modifier
					.width(if (it == selectedPage) 35.dp else 15.dp)
					.height(15.dp)
					.clip(CircleShape)
					.background(color = if (it == selectedPage) selectedColor else unselectedColor)
					
			)
		}
	}
	
}
@Composable
fun NewsButton(
	text: String,
	onClick: () -> Unit,
) {
	
	Button(
		onClick = onClick,
		colors = ButtonDefaults.buttonColors(
			containerColor = MaterialTheme.colorScheme.primary,
			contentColor = Color.White
		),
		shape = RoundedCornerShape(size = 6.dp)
	) {
		Text(
			text = text,
			style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
		)
	}
}

@Composable
fun NewsTextButton(
	text: String,
	onClick: () -> Unit,
) {
	TextButton(onClick = onClick) {
		Text(
			text = text,
			style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold),
			color = Color.Gray
		)
	}
}

@Serializable
object OnBoardingScreen