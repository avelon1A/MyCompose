package com.bosch.composewithkotlin20.presentaion.ui.screen.animationScreens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.serialization.Serializable


@Preview(showBackground = true)
@Composable
fun WaterBottel(){

    Box(modifier = Modifier.fillMaxSize()){
        Canvas(modifier = Modifier.matchParentSize()){

            val path = Path().apply {

                moveTo(0f, size.height/2)

                lineTo(100f, size.height/2)
                quadraticTo(
                    100f,size.height/2 -100f,
                    size.height/2 -100f,size.height/2 )
                lineTo(size.width, size.height/2)
                lineTo(size.width, size.height)
                lineTo(0f, size.height)


               close()


            }
            drawPath(
                path= path, color = Color(0XFF160C28)
            )

        }


    }
}
@Serializable
object WaterBottel