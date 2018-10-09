package uk.co.tezk.themeadow.preferences

import android.content.Context
import android.content.SharedPreferences

class MeadowPreferences {

    fun set(key: String, value: String) {
        preferences.edit().putString(key, value).commit()
    }

    fun get(key: String) =
            preferences.getString(key, "")

    companion object {
        lateinit var preferences: SharedPreferences
        lateinit var meadowPreferences: MeadowPreferences

        const val PREFERENCES_NAME = "meadowprefs"

        fun initialise(context: Context) {
            preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
            meadowPreferences = MeadowPreferences()
        }

        fun getInstance(): MeadowPreferences = meadowPreferences

        const val CALENDAR_URL = "calendarurl"
    }
}