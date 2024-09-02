package com.example.placarpdm.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesUtil private constructor(context: Context){
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("PlacarPDM", Context.MODE_PRIVATE)

    companion object{
        @Volatile
        private var INSTANCE: SharedPreferencesUtil? = null

        fun getInstance(context: Context): SharedPreferencesUtil{
            return INSTANCE ?: synchronized(this){
                INSTANCE ?: SharedPreferencesUtil(context).also { INSTANCE = it }
            }
        }
    }

    fun saveData(key: String, value: String){
        sharedPreferences.edit().putString(key, value).apply()
    }

    fun getData(key: String): String?{
        return sharedPreferences.getString(key, "")
    }
}