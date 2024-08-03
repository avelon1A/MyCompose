package com.bosch.composewithkotlin20.presentaion.ui.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosch.composewithkotlin20.domain.usecases.AppEntryUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainViewModel (appEntryUseCase: AppEntryUseCase ) : ViewModel()  {
	
	 var _splashCondition by mutableStateOf(true)
	private set
	var _startDestination  by mutableStateOf("")
		private set
	
	
	
	init {
		appEntryUseCase.getAppEntry().onEach { entryStatus ->
		 _startDestination = if (entryStatus){
			 "home"
		 } else {
			 "onBoarding"
		 }
			delay(300)
			_splashCondition = false
		}.launchIn(viewModelScope )
	}
}