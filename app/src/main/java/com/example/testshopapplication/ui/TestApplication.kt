package com.example.testshopapplication.ui

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TestApplication: Application() {

//    override fun attachBaseContext(base: Context) {
////        OpenPreferences.init(base)
////        super.attachBaseContext(LocaleHelper.onAttach(base))
//    }
}