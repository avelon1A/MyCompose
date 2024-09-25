package com.bosch.composewithkotlin20.presentaion.ui.screen.supabase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosch.composewithkotlin20.data.model.data.Cafe
import com.bosch.composewithkotlin20.domain.repo.SupabaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SupaBaseViewModel(private val supabaseRepository: SupabaseRepository) : ViewModel() {

    private val _cafeList = MutableStateFlow<List<Cafe>>(emptyList())
    val cafeList: StateFlow<List<Cafe>> = _cafeList

    init {
        fetchInitialCafeList()
        observeRealtimeCafeUpdates()
    }

    private fun fetchInitialCafeList() {
        viewModelScope.launch {
            supabaseRepository.fetchCafes().collect { cafeList ->
                _cafeList.value = cafeList
            }
        }
    }

    private fun observeRealtimeCafeUpdates() {
        viewModelScope.launch {
            supabaseRepository.observeRealtimeCafes().collect { cafeList ->
                _cafeList.value = cafeList
            }
        }
    }
}
