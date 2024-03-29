package com.example.android.presentation

import android.app.Application
import com.example.android.presentation.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(appModules)
            //modules(userListModule)
        }
    }
}