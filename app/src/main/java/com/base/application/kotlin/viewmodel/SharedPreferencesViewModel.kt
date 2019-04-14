package com.base.application.kotlin.viewmodel

import android.arch.lifecycle.ViewModel
import com.base.application.kotlin.model.repositories.SharedPreferencesRepository

class SharedPreferencesViewModel(private var sharedPreferencesRepository: SharedPreferencesRepository) : ViewModel(){

    fun getString() : String? {
        return sharedPreferencesRepository.getString()
    }

    fun saveString(string : String): Boolean {
        return sharedPreferencesRepository.saveString(string)
    }

    fun clear(): Boolean {
        return sharedPreferencesRepository.clear()
    }
}