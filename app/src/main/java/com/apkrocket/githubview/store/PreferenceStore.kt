package com.apkrocket.githubview.store

import android.content.SharedPreferences
import javax.inject.Inject

class PreferenceStore @Inject constructor(val sharedPreferences: SharedPreferences) {

    companion object {
        const val PREF_CURRENT_USER_UID = "current_user.uid"
    }

    fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }

}