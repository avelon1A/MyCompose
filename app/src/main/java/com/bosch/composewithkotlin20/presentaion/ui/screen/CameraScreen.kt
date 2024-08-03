package com.bosch.composewithkotlin20.presentaion.ui.screen

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.serialization.Serializable
import java.io.File
import java.io.IOException

@Composable
fun CameraScreen() {
	val context = LocalContext.current
	val activity = context as? Activity
	var imageUri by rememberSaveable { mutableStateOf<Uri?>(null) }
	var imageBitmap by rememberSaveable { mutableStateOf<Bitmap?>(null) }
	val requestPermissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission())
	
	{ isGranted: Boolean ->
		if (isGranted) {
			openCamera(activity) { uri ->
				imageUri = uri   }
		} else {
			// Handle permission denial
		}
	}
	
	val takePictureLauncher = rememberLauncherForActivityResult(
		ActivityResultContracts.TakePicture())
	{ success ->
		if (success)
		{
			imageUri?.let { uri -> imageBitmap = loadImageFromUri(context, uri) }
		}
	}
	
	Column(
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center,
		modifier = Modifier
			.fillMaxSize()
			.padding(16.dp))
	{
		Button(
			onClick = {
				when
				{
					ContextCompat.checkSelfPermission( context, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED -> {
						openCamera(activity)
						{ uri ->
							imageUri = uri
							takePictureLauncher.launch(uri)
						} }
					
					else -> {
						requestPermissionLauncher.launch(android.Manifest.permission.CAMERA)
					}
				}
			},
			colors = ButtonDefaults.buttonColors(
				containerColor = Color.LightGray,
				contentColor = Color.White
			),
			shape = RoundedCornerShape(8.dp),
			modifier = Modifier.padding(vertical = 16.dp)
		) {
			Text(text = "Open Camera")
		}
		
		imageBitmap?.let { bitmap ->
			Image(
				bitmap = bitmap.asImageBitmap(),
				contentDescription = "Captured Image",
				modifier = Modifier
					.height(200.dp)
					.width(200.dp)
			)
		}
	}
}

private fun openCamera(activity: Activity?, onImageUriCreated: (Uri) -> Unit) {
	activity?.let {
		val photoFile = createImageFile(it)
		val photoURI: Uri = FileProvider.getUriForFile( it,
			it.applicationContext.packageName + ".provider",
			photoFile
		)
		onImageUriCreated(photoURI)
	}
}

private fun createImageFile(context: Context): File {
	val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
	return File.createTempFile(
		"JPEG_${System.currentTimeMillis()}_",
		".jpg",
		storageDir
	).apply {
		currentPhotoPath = absolutePath
	}
}

private fun loadImageFromUri(context: Context, uri: Uri): Bitmap? {
	return try {
		val source = ImageDecoder.createSource(context.contentResolver, uri)
		ImageDecoder.decodeBitmap(source)
	} catch (e: IOException) {
		e.printStackTrace()
		null
	}
}

private lateinit var currentPhotoPath: String
@Serializable
object CameraScreen