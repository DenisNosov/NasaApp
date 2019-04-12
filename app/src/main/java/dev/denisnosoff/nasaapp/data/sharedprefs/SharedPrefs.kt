package dev.denisnosoff.nasaapp.data.sharedprefs

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import dev.denisnosoff.nasaapp.R

class SharedPrefs(context: Context) {

    private val PREFS_NAME = "${context.getString(R.string.app_name)}.prefs"
    private val DELETED_PHOTOS = "DELETED_PHOTOS"

    private val preferences: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    var photosList: ArrayList<Int>
        get() {
            val listString = preferences.getString(DELETED_PHOTOS, "")
            return TextUtils.split(listString, ",‗,")
                .map { it.toInt() }
                .toCollection(ArrayList())
    }
        set(value) {
            val stringArray = value.toArray() ?: Array(0){}
            preferences.edit().putString(
                DELETED_PHOTOS,
                TextUtils.join(
                    ",‗,",
                    stringArray.map { it.toString() }))
                .apply()
    }

}