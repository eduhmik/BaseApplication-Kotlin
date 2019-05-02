package com.base.application.kotlin.di

import androidx.room.Room
import android.content.Context
import android.content.SharedPreferences
import com.base.application.kotlin.di.Properties.BASE_URL
import com.base.application.kotlin.di.Properties.ROOM_DB_NAME
import com.base.application.kotlin.di.Properties.SHARED_PREFERENCES
import com.base.application.kotlin.model.repositories.FooRestRepository
import com.base.application.kotlin.model.repositories.FooRoomRepository
import com.base.application.kotlin.model.repositories.SharedPreferencesRepository
import com.base.application.kotlin.model.rest.FooRestApi
import com.base.application.kotlin.model.room.AppDatabase
import com.base.application.kotlin.viewmodel.FooRestViewModel
import com.base.application.kotlin.viewmodel.FooRoomViewModel
import com.base.application.kotlin.viewmodel.SharedPreferencesViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Koin main module
 */
val MainAppModule = module{
    factory<SharedPreferences>{ androidContext().getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE)}
    //Inject shared prefs repo as singleton
    single { SharedPreferencesRepository(get()) }
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
 * Room module
 */
val RoomModule = module {
    // Room Database instance
    single{Room.databaseBuilder(androidApplication(), AppDatabase::class.java, ROOM_DB_NAME).build()}
    // FooDAO instance (get instance from AppDatabase)
    single { get<AppDatabase>().fooDao() }
}

/**
 * Network module
 */
val NetworkModule = module {
    //OkHttp client
    single { createOkHttpClient()}
    //Foo Rest API
    single{ createWebService<FooRestApi>(get(), getProperty(BASE_URL)) }
}


/**
 * Module list
 */
val appModules = listOf(MainAppModule, ViewModelsModule, NetworkModule, RoomModule)

object Properties {
    const val SHARED_PREFERENCES = "Shared Preferences"
    const val ROOM_DB_NAME = "foo.db"
    const val BASE_URL = "https://api.github.com"
}



fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory()).build()
    return retrofit.create(T::class.java)
}


