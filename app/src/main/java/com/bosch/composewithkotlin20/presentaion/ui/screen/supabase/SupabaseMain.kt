package com.bosch.composewithkotlin20.presentaion.ui.screen.supabase

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.bosch.composewithkotlin20.data.model.data.Cafe
import com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI.CoilImage
import kotlinx.serialization.Serializable

@Composable
fun SupaBaseMainScreen(supaBaseViewModel: SupaBaseViewModel) {


    val cafeList by supaBaseViewModel.cafeList.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {
            items(cafeList.size) {
                CafeItem(cafe = cafeList[it])

            }

        }


    }

}

@Composable
fun CafeItem(cafe: Cafe) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .height(60.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.Green.copy(alpha = 0.3f)),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CoilImage(
            image = cafe.image,
            modifier = Modifier
                .size(100.dp)
                .padding(10.dp),
            contentScale = ContentScale.Crop
        )
        Text(text = cafe.name, modifier = Modifier.padding(end = 10.dp))
    }
}

@Serializable
object SupaBaseMainScreen

