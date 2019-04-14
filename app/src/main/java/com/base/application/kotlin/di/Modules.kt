package com.base.application.kotlin.di

import android.content.Context
import android.content.SharedPreferences
import com.base.application.kotlin.di.Properties.SHARED_PREFERENCES
import com.base.application.kotlin.model.repositories.SharedPreferencesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

/**
 * Koin main module
 */
val MainAppModule = module{
    factory<SharedPreferences>{ androidContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)}
    single { SharedPreferencesRepository(get()) }
}


/**
 * Module list
 */
val appModules = listOf(MainAppModule)

object Properties {
    const val SHARED_PREFERENCES = "Shared Preferences"
}


