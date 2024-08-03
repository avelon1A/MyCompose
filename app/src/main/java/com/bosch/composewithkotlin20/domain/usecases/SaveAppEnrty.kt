package com.bosch.composewithkotlin20.domain.usecases

import com.bosch.composewithkotlin20.domain.manger.LocalUserManager
import kotlinx.coroutines.flow.Flow

class SaveAppEntry (
	 private val localUserManager: LocalUserManager
){
suspend operator fun  invoke(){
	localUserManager.saveAppEntry()
}
}
class GetAppEntry(
	 private val localUserManager: LocalUserManager
) {
	operator fun invoke(): Flow<Boolean> {
		return localUserManager.readAppEntry()
	}
}
data class AppEntryUseCase (
	val saveAppEntry: SaveAppEntry,
	val getAppEntry: GetAppEntry

)