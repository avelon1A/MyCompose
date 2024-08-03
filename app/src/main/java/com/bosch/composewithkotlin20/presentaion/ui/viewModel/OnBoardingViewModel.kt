package com.bosch.composewithkotlin20.presentaion.ui.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosch.composewithkotlin20.domain.usecases.AppEntryUseCase
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.event.OnBoardingEvent
import kotlinx.coroutines.launch

class OnBoardingViewModel(
	private val appEntryUseCase: AppEntryUseCase
) : ViewModel() {
	fun OnEvent(event: OnBoardingEvent) {
		when(event){
		  is OnBoardingEvent.SaveAtEntryPoint  -> {
			  saveAppEntry()
		  }
		}
	}
	
	private fun saveAppEntry() {
		viewModelScope.launch {
			appEntryUseCase.saveAppEntry()
		}
	}
}