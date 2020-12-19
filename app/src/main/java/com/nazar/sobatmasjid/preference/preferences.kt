package com.nazar.sobatmasjid.preference

import android.content.Context
import android.content.SharedPreferences
import android.location.Location
import androidx.core.content.edit
import com.nazar.sobatmasjid.data.local.entity.CityEntity
import com.nazar.sobatmasjid.data.remote.response.UserResponse

class Preferences(context: Context){

    companion object {
        private const val PREFS_FILENAME = "SOBAT_MASJID_PREFS"
        private const val KEY_FIRST_TIME = "KEY_FIRST_TIME"
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
        private const val KEY_API_CODE = "KEY_API_CODE"
        private const val KEY_LATITUDE = "KEY_LATITUDE"
        private const val KEY_LONGITUDE = "KEY_LONGITUDE"
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

    var numberFollow: Int
        get() = sharedPrefs.getInt(KEY_NUMBER_FOLLOW, 0)
        set(value) = sharedPrefs.edit { putInt(KEY_NUMBER_FOLLOW, value) }

    var photoUrl: String
        get() = sharedPrefs.getString(KEY_PHOTO_URL, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_PHOTO_URL, value) }

    var nameCity: String
        get() = sharedPrefs.getString(KEY_NAME_CITY, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_NAME_CITY, value) }

    var apiCode: String
        get() = sharedPrefs.getString(KEY_API_CODE, "") ?: ""
        set(value) = sharedPrefs.edit { putString(KEY_API_CODE, value) }

    var isNotFirstTime: Boolean
        get() = sharedPrefs.getBoolean(KEY_FIRST_TIME, false)
        set(value) = sharedPrefs.edit { putBoolean(KEY_FIRST_TIME, value) }

    var latitude: Double
        get() = sharedPrefs.getString(KEY_LATITUDE, "")?.toDouble() ?: 0.0
        set(value) = sharedPrefs.edit { putString(KEY_LATITUDE, value.toString()) }

    var longitude: Double
        get() = sharedPrefs.getString(KEY_LONGITUDE, "")?.toDouble() ?: 0.0
        set(value) = sharedPrefs.edit { putString(KEY_LONGITUDE, value.toString()) }

    fun setPreference(userResponse: UserResponse, location: Location){
        sharedPrefs.edit { putString(KEY_ID_USER, userResponse.id) }
        sharedPrefs.edit { putString(KEY_ID_CITY, userResponse.idCity) }
        sharedPrefs.edit { putString(KEY_NAME, userResponse.name) }
        sharedPrefs.edit { putString(KEY_BORN_DATE, userResponse.bornDate) }
        sharedPrefs.edit { putString(KEY_EMAIL, userResponse.email) }
        sharedPrefs.edit { putString(KEY_GENDER, userResponse.gender) }
        sharedPrefs.edit { putString(KEY_MOTTO, userResponse.motto) }
        sharedPrefs.edit { putInt(KEY_NUMBER_FOLLOW, userResponse.numberFollow?.toInt() ?: 0) }
        sharedPrefs.edit { putString(KEY_PHOTO_URL, userResponse.photo) }
        sharedPrefs.edit { putString(KEY_NAME_CITY, userResponse.nameCity) }
        sharedPrefs.edit { putString(KEY_API_CODE, userResponse.apiCode) }
        sharedPrefs.edit { putString(KEY_LATITUDE, location.latitude.toString()) }
        sharedPrefs.edit { putString(KEY_LONGITUDE, location.longitude.toString()) }
    }

    fun setPreferenceUserOnly(userResponse: UserResponse){
        sharedPrefs.edit { putString(KEY_ID_USER, userResponse.id) }
        sharedPrefs.edit { putString(KEY_ID_CITY, userResponse.idCity) }
        sharedPrefs.edit { putString(KEY_NAME, userResponse.name) }
        sharedPrefs.edit { putString(KEY_BORN_DATE, userResponse.bornDate) }
        sharedPrefs.edit { putString(KEY_EMAIL, userResponse.email) }
        sharedPrefs.edit { putString(KEY_GENDER, userResponse.gender) }
        sharedPrefs.edit { putString(KEY_MOTTO, userResponse.motto) }
        sharedPrefs.edit { putInt(KEY_NUMBER_FOLLOW, userResponse.numberFollow?.toInt() ?: 0) }
        sharedPrefs.edit { putString(KEY_PHOTO_URL, userResponse.photo) }
        sharedPrefs.edit { putString(KEY_NAME_CITY, userResponse.nameCity) }
        sharedPrefs.edit { putString(KEY_API_CODE, userResponse.apiCode) }
    }

    fun setCity(city: CityEntity){
        sharedPrefs.edit { putString(KEY_ID_CITY, city.id) }
        sharedPrefs.edit { putString(KEY_NAME_CITY, city.name) }
        sharedPrefs.edit { putString(KEY_API_CODE, city.apiCode) }
    }
}