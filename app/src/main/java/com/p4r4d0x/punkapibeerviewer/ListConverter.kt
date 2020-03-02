package com.p4r4d0x.punkapibeerviewer

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.util.*


class ListConverter {


    @TypeConverter
    fun fromStringAL(value: String?): ArrayList<String?>? {
        val listType =
            object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }

    @TypeConverter
    fun fromStringL(value: String?): List<String?>? {
        val listType =
            object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }


    @TypeConverter
    fun fromStringMethod(value: String?): com.p4r4d0x.punkapibeerviewer.model.Method? {

        return Gson().fromJson(value, com.p4r4d0x.punkapibeerviewer.model.Method::class.java)
    }

    @TypeConverter
    fun fromMethod(method: com.p4r4d0x.punkapibeerviewer.model.Method?): String? {
        val gson = Gson()
        return gson.toJson(method)
    }

    @TypeConverter
    fun fromStringBoilVolume(value: String?): com.p4r4d0x.punkapibeerviewer.model.BoilVolume? {

        return Gson().fromJson(value, com.p4r4d0x.punkapibeerviewer.model.BoilVolume::class.java)
    }

    @TypeConverter
    fun fromBoilVolume(method: com.p4r4d0x.punkapibeerviewer.model.BoilVolume?): String? {
        val gson = Gson()
        return gson.toJson(method)
    }

    @TypeConverter
    fun fromStringVolume(value: String?): com.p4r4d0x.punkapibeerviewer.model.Volume? {

        return Gson().fromJson(value, com.p4r4d0x.punkapibeerviewer.model.Volume::class.java)
    }

    @TypeConverter
    fun fromVolume(method: com.p4r4d0x.punkapibeerviewer.model.Volume?): String? {
        val gson = Gson()
        return gson.toJson(method)
    }

    @TypeConverter
    fun fromStringIngredients(value: String?): com.p4r4d0x.punkapibeerviewer.model.Ingredients? {

        return Gson().fromJson(value, com.p4r4d0x.punkapibeerviewer.model.Ingredients::class.java)
    }

    @TypeConverter
    fun fromIngredients(method: com.p4r4d0x.punkapibeerviewer.model.Ingredients?): String? {
        val gson = Gson()
        return gson.toJson(method)
    }
}