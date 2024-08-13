package com.bosch.composewithkotlin20.di

import android.content.Context
import androidx.annotation.OptIn
import androidx.lifecycle.SavedStateHandle
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.session.MediaSession
import com.bosch.composewithkotlin20.data.api.ApiService
import com.bosch.composewithkotlin20.data.manager.LocalUserMangerImp
import com.bosch.composewithkotlin20.data.repo.AudioRepository
import com.bosch.composewithkotlin20.data.repo.LoginRepository
import com.bosch.composewithkotlin20.domain.manger.LocalUserManager
import com.bosch.composewithkotlin20.domain.usecases.AppEntryUseCase
import com.bosch.composewithkotlin20.domain.usecases.GetAppEntry
import com.bosch.composewithkotlin20.domain.usecases.SaveAppEntry
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.AudioViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.LoginViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.MainViewModel
import com.bosch.composewithkotlin20.presentaion.ui.viewModel.OnBoardingViewModel
import com.bosch.composewithkotlin20.util.ServiceStarter
import com.bosch.composewithkotlin20.util.service.mediaPlayer.ContentResolverHelper
import com.bosch.composewithkotlin20.util.service.mediaPlayer.JetAudioNotificationManager
import com.bosch.composewithkotlin20.util.service.mediaPlayer.JetAudioServiceHandler
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
	
	single { provideRetrofit() }
	single { provideApiService(get()) }
	single<LocalUserManager> { LocalUserMangerImp(androidApplication()) }
	single { provideAudioAttributes() }
	single { JetAudioNotificationManager(androidContext(), get()) }
	single { JetAudioServiceHandler(get()) }
	single { ServiceStarter(androidContext()) }
	single { ContentResolverHelper(androidContext()) }
	single { AudioRepository(get()) }
	single { SaveAppEntry(get()) }
	single { GetAppEntry(get()) }
	single { LoginRepository(get()) }
	single { provideExoPlayer(androidContext(), get())}
	single { provideMediaSession(get(), get()) }
	
	
	single { AppEntryUseCase(get(), get()) }
	viewModel { MainViewModel(get()) }
	
	viewModel { LoginViewModel(get()) }
	viewModel { (savedStateHandle: SavedStateHandle) ->
		AudioViewModel(
			get(),
			get(),
			get(),
			savedStateHandle
		)
	}
	viewModel { OnBoardingViewModel(get()) }
}

fun provideRetrofit(): Retrofit {
	return Retrofit.Builder()
		.baseUrl("https://dummyjson.com/")
		.addConverterFactory(GsonConverterFactory.create())
		.build()
}

fun provideApiService(retrofit: Retrofit): ApiService {
	return retrofit.create(ApiService::class.java)
}

fun provideAudioAttributes(): AudioAttributes {
	return AudioAttributes.Builder()
		.setContentType(C.AUDIO_CONTENT_TYPE_MOVIE)
		.setUsage(C.USAGE_MEDIA)
		.build()
}

@OptIn(UnstableApi::class)
fun provideExoPlayer(context: Context, audioAttributes: AudioAttributes): ExoPlayer {
	return ExoPlayer.Builder(context)
		.setAudioAttributes(audioAttributes, true)
		.setHandleAudioBecomingNoisy(true)
		.setTrackSelector(DefaultTrackSelector(context))
		.build()
}

fun provideMediaSession(context: Context, player: ExoPlayer): MediaSession {
	return MediaSession.Builder(context, player).build()
}