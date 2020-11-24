package com.nazar.sobatmasjid.preference

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Preferences(context: Context){

    companion object {
        private const val PREFS_FILENAME = "SOBAT_MASJID_PREFS"
        private const val KEY_ID_USER = "KEY_ID_USER"
        private const val KEY_ID_CITY = "KEY_ID_CITY"
        private const val KEY_NAME = "KEY_NAME"
        private const val KEY_BORN_DATE = "KEY_BORN_DATE"
        private const val KEY_EMAIL = "KEY_EMAIL"
        private const val KEY_GENDER = "KEY_GENDER"
        private const val KEY_MOTTO = "KEY_MOTTO"
        private const val KEY_NUMBER_FOLLOW = "KEY_NUMBER_FOLLOW"
        private const val KEY_PHOTO_URL = "KEY_PHOTO_URL"
        private const val KEY_NAME_CITY = "KEY_NAME_CITY"
    }

    private val sharedPrefs: SharedPreferences =
        context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var idUser: String
        get() = sharedPrefs.getString(KEY_ID_USER, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_ID_USER, value) }

    var idCity: String
        get() = sharedPrefs.getString(KEY_ID_CITY, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_ID_CITY, value) }

    var name: String
        get() = sharedPrefs.getString(KEY_NAME, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_NAME, value) }

    var bornDate: String
        get() = sharedPrefs.getString(KEY_BORN_DATE, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_BORN_DATE, value) }

    var email: String
        get() = sharedPrefs.getString(KEY_EMAIL, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_EMAIL, value) }

    var gender: String
        get() = sharedPrefs.getString(KEY_GENDER, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_GENDER, value) }

    var motto: String
        get() = sharedPrefs.getString(KEY_MOTTO, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_MOTTO, value) }

    var numberFollow: String
        get() = sharedPrefs.getString(KEY_NUMBER_FOLLOW, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_NUMBER_FOLLOW, value) }

    var photoUrl: String
        get() = sharedPrefs.getString(KEY_PHOTO_URL, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_PHOTO_URL, value) }

    var nameCity: String
        get() = sharedPrefs.getString(KEY_NAME_CITY, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_NAME_CITY, value) }

}