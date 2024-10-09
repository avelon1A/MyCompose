package com.bosch.composewithkotlin20.presentaion.ui.screen.basicUI

import androidx.compose.ui.geometry.Size
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.serialization.Serializable

@Composable
fun CircularIndicatorScreen(){


}
@Composable
fun CircularIndicator(
	canvasSize:Dp = 300.dp,
	indicatorValue:Int = 0,
	maxIndicatorValue:Int = 100,
	backgroundIndicatorColor:Color = MaterialTheme.colorScheme.primary ,
	backgroundIndicatorStrokeWidth:Float = 100f
){
	
	
	Column(modifier = Modifier.size(canvasSize)
		.drawBehind {
			val componentSize = size / 1.25f
			backgroundIndicator(
				componentSize = componentSize,
				indicatorColor = backgroundIndicatorColor,
				indicatorStrokeWidth = backgroundIndicatorStrokeWidth
				
			)
		}
	
	) {  }
	
}
fun DrawScope.backgroundIndicator(
	componentSize: Size,
	indicatorColor: Color,
	indicatorStrokeWidth: Float
	){
drawArc(
	color = indicatorColor,
	startAngle = 150f,
	sweepAngle = 240f,
	useCenter = false,
	style= Stroke(width = indicatorStrokeWidth,
		cap = StrokeCap.Round),
	topLeft = Offset(
		x = (size.width - componentSize.width) / 2f,
		y = (size.height - componentSize.height) / 2.5f
	)
)

}
@Preview(showBackground = true)
@Composable
fun CircularIndicatorPreview(){
	CircularIndicator()
}

@Serializable
object CircularIndicatorScreen

