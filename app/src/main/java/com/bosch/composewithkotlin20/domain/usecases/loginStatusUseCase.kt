package com.bosch.composewithkotlin20.domain.usecases

import com.bosch.composewithkotlin20.domain.manger.LocalUserManager

class LoginStatus( private val localUserManager: LocalUserManager) {
    suspend operator fun  invoke(){
        localUserManager.loginStatus()
    }
}