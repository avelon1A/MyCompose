package com.bosch.composewithkotlin20.presentaion.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.rememberNavController
import com.bosch.composewithkotlin20.domain.navigation.AppNavigator
import com.bosch.composewithkotlin20.domain.usecases.AppEntryUseCase
import com.bosch.composewithkotlin20.presentaion.navigation.AppNavHost
import com.bosch.composewithkotlin20.presentaion.navigation.AppNavigationImp
import com.bosch.composewithkotlin20.presentaion.navigation.navigation
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.MainViewModel
import com.example.compose.AppTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberPermissionState
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

	lateinit var appEntryUseCase: AppEntryUseCase
	private val viewModel: MainViewModel by viewModel()
	
	@OptIn(ExperimentalPermissionsApi::class)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		installSplashScreen().apply {
			setKeepOnScreenCondition {
				viewModel._splashCondition
			}
		}
//		enableEdgeToEdge()
		setContent {
			AppTheme {
				val permissionState = rememberPermissionState(
					permission = Manifest.permission.READ_EXTERNAL_STORAGE
				)
				val lifecycleOwner = LocalLifecycleOwner.current
				DisposableEffect(key1 = lifecycleOwner) {
					val observer = LifecycleEventObserver { _, event ->
						if (event == Lifecycle.Event.ON_RESUME) {
							permissionState.launchPermissionRequest()
						}
					}
					lifecycleOwner.lifecycle.addObserver(observer)
					onDispose {
						lifecycleOwner.lifecycle.removeObserver(observer)
					}
				}
				Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
					val navController = rememberNavController()
					AppNavHost(
						navController = navController,
						modifier = Modifier.padding(innerPadding),
						appNavigator = AppNavigationImp(navController),
						startDestination = navigation(viewModel._startDestination)
					)
					
				}
			}
		}
	}
	

}


