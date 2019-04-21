package com.base.application.kotlin.di

import android.arch.persistence.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.base.application.kotlin.di.Properties.ROOM_DB_NAME
import com.base.application.kotlin.di.Properties.SHARED_PREFERENCES
import com.base.application.kotlin.model.repositories.FooRestRepository
import com.base.application.kotlin.model.repositories.FooRoomRepository
import com.base.application.kotlin.model.repositories.SharedPreferencesRepository
import com.base.application.kotlin.model.room.AppDatabase
import com.base.application.kotlin.viewmodel.FooRestViewModel
import com.base.application.kotlin.viewmodel.FooRoomViewModel
import com.base.application.kotlin.viewmodel.SharedPreferencesViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin main module
 */
val MainAppModule = module{
    factory<SharedPreferences>{ androidContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)}
    //Inject shared prefs repo as singleton
    single { SharedPreferencesRepository(get()) }
    // Room Database instance
    single{Room.databaseBuilder(androidApplication(), AppDatabase::class.java, ROOM_DB_NAME).build()}
    // FooDAO instance (get instance from AppDatabase)
    single { get<AppDatabase>().fooDao() }
    // FooRoomRepository instance
    single { FooRoomRepository(get()) }
    // FooRestRepository instance
    single {FooRestRepository(get())}
}

/**
 * ViewModels module
 */
val ViewModelsModule = module {
    //Provides an instance of ViewModel and binds it to an Android Component lifecycle
    viewModel { FooRoomViewModel(get()) }
    viewModel { SharedPreferencesViewModel(get()) }
    viewModel { FooRestViewModel(get()) }
}


/**
 * Module list
 */
val appModules = listOf(MainAppModule, ViewModelsModule)

object Properties {
    const val SHARED_PREFERENCES = "Shared Preferences"
    const val ROOM_DB_NAME = "foo.db"
}


