package com.base.application.kotlin.model.repositories

import android.content.SharedPreferences

class SharedPreferencesRepository(private val sharedPreferences: SharedPreferences) {

    fun getString() : String? {
        return sharedPreferences.getString(THIS_STRING_PREFS, "")
    }

    fun saveString(string : String): Boolean {
        return sharedPreferences.edit().putString(THIS_STRING_PREFS, string).commit()
    }

    fun clear(): Boolean {
        return sharedPreferences.edit().clear().commit()
    }

    companion object {
        const val THIS_STRING_PREFS = "This string prefs"
    }
}