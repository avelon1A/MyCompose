package com.bosch.composewithkotlin20.presentaion.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import kotlinx.serialization.Serializable

data class BottomNavigationItem(
	val title: String,
	val selectedIcon: ImageVector,
	val unselectedIcon: ImageVector,
	val hasBadge: Boolean = false,
	val badgeCount: Int? = null,
	val route: String
)

@Composable
fun BottomNavigationBar() {
	
	val items = listOf(
		BottomNavigationItem(
			title = "Home",
			selectedIcon = Icons.Filled.Home,
			unselectedIcon = Icons.Outlined.Home,
			hasBadge = false,
			badgeCount = null,
			route = "home"
		),
		BottomNavigationItem(
			title = "Search",
			selectedIcon = Icons.Filled.Search,
			unselectedIcon = Icons.Outlined.Search,
			hasBadge = false,
			badgeCount = 5,
			route = "search"
		),
		BottomNavigationItem(
			title = "Setting",
			selectedIcon = Icons.Filled.Settings,
			unselectedIcon = Icons.Outlined.Settings,
			hasBadge = true,
			badgeCount = null,
			route = "setting",
		)
	)
	var selected by rememberSaveable { mutableIntStateOf(0) }
	Scaffold(
		bottomBar = {
			NavigationBar(containerColor = MaterialTheme.colorScheme.primary,
				contentColor = MaterialTheme.colorScheme.secondary,){
				items.forEachIndexed { index, item ->
					NavigationBarItem(
						colors = NavigationBarItemDefaults.colors(MaterialTheme.colorScheme.secondary),
						selected = selected == index,
						onClick = { selected = index },
						icon = {
							BadgedBox(badge = {
								if (item.badgeCount != null) {
									Badge {
										Text(item.badgeCount.toString())
									}
								} else if (item.hasBadge) {
									Badge()
								}
							}) {
								Icon(
									imageVector = if (selected == index) item.selectedIcon else item.unselectedIcon,
									contentDescription = item.title
								)
							}
						},
						label = { Text(item.title) }
					)
				}
			}
		}
	) {
		Column(
			modifier = Modifier
				.padding(it)
				.fillMaxSize(),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.Center
		) {
			Text(text = selected.toString())
		}
		
	}
	
}

@Serializable
object BottomNavigationBar