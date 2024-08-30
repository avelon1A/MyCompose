package com.bosch.composewithkotlin20.presentation.ui.screen.supabase

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bosch.composewithkotlin20.data.model.data.Cafe
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.annotations.SupabaseExperimental
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.selectAsFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SupaBaseViewModel(private val supabaseClient: SupabaseClient) : ViewModel() {

    private val _cafeList = MutableStateFlow<List<Cafe>>(emptyList())
    val cafeList: StateFlow<List<Cafe>> = _cafeList

    init {
        fetchInitialCafeList()
        observeRealtimeCafeUpdates()
    }

    private fun fetchInitialCafeList() {
        viewModelScope.launch {
            try {
                val cafes = supabaseClient.from("cafe")
                    .select()
                    .decodeList<Cafe>()
                _cafeList.value = cafes
            } catch (e: Exception) {
                Log.d("SupabaseViewModel", "Error observing  updates: ${e.message}")
            }
        }
    }


    @OptIn(SupabaseExperimental::class)
    private fun observeRealtimeCafeUpdates() {
        viewModelScope.launch {
            try {
                supabaseClient.from("cafe")
                    .selectAsFlow(Cafe::id)
                    .collect { updatedCafe ->
                        _cafeList.value = updatedCafe

                    }
            } catch (e: Exception) {
            Log.d("SupabaseViewModel", "Error observing realtime updates: ${e.message}")
            }
        }
    }
}
