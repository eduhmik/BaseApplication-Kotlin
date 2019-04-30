package com.base.application.kotlin.view.app

import android.app.Application
import com.base.application.kotlin.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController : Application(){

    override fun onCreate() {
        super.onCreate()

        // start Koin context
        startKoin  {
            // Use Android Context from MainApplication
            androidContext(this@AppController)
            // Load modules
            modules(appModules)
        }
    }
}