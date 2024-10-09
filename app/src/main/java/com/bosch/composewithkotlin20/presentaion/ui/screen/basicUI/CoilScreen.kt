package com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.bosch.composewithkotlin20.R
import kotlinx.serialization.Serializable

@Composable
fun CoilScreen(){
	
	Column(modifier = Modifier.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center) {
		CoilImage(modifier = Modifier)
		
	}

}
@Composable
fun CoilImage(image:String = "https://miro.medium.com/v2/resize:fit:1400/format:webp/0*QtZA5PZkzL2XUpYl.jpg",
			  modifier: Modifier,
			  contentScale: ContentScale = ContentScale.Fit,
){
	Box(modifier = modifier.height(200.dp)
		.width(200.dp),
		contentAlignment = Alignment.Center
		) {
		SubcomposeAsyncImage(
			model = image,
			contentScale = contentScale,
			contentDescription = stringResource(R.string.description)
		) {
			val state = painter.state
			
			if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
				CircularProgressIndicator()
			} else {
				SubcomposeAsyncImageContent()
			}
		}
	}
}
@Serializable
object CoilScreen