package com.example.testshopapplication.ui.base

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {

    open val viewModel: BaseViewModel? = null

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(LocaleHelper.onAttach(newBase))
    }

}