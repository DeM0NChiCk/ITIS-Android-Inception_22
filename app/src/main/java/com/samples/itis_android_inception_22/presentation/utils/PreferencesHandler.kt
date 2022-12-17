package com.samples.itis_android_inception_22.presentation.utils

import android.content.Context
import android.content.SharedPreferences

object PreferencesHandler {

    private var preferences: SharedPreferences? = null

    fun createPreferences(ctx: Context) {
        if (preferences == null) {
            preferences = ctx.getSharedPreferences("prefFileName", Context.MODE_PRIVATE)
        } else return
    }

    fun saveUsername(username: String) {
        preferences?.edit()?.apply {
            this.putString("USERNAME_KEY", username)
                .apply()
        }
    }

    fun getUsername(): String {
        return preferences?.getString("USERNAME_KEY", "sample") ?: ""
    }

    fun saveStringValue(key: String, input: String) {
        preferences?.edit()?.putString(key, input)?.apply()
    }

    fun getStringValue(key: String): String? {
        return preferences?.getString(key, null)
    }
}