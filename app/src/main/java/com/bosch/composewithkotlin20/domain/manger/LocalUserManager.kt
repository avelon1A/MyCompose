package com.bosch.composewithkotlin20.domain.manger

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
	suspend fun saveAppEntry()
	fun readAppEntry(): Flow<Boolean>
	
}