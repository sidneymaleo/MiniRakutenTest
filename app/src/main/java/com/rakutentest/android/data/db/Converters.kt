package com.rakutentest.android.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun restoreList(listOfString: String): List<String> {
        return Gson().fromJson(listOfString, object : TypeToken<List<String?>?>() {}.type)
    }
    @TypeConverter
    fun saveList(listOfString: List<String>) : String{
        return Gson().toJson(listOfString)
    }
}