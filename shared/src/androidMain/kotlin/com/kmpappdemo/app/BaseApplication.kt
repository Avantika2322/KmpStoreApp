package com.kmpappdemo.app

import android.app.Application
import com.kmpappdemo.app.di.initKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}