package com.p4r4d0x.punkapibeerviewer.utilities

import androidx.room.TypeConverter
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import java.util.*


class DatabaseConverters {

    @TypeConverter
    fun fromStringL(value: String?): List<String?>? {
        val listType = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String?>?): String? {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromStringMethod(value: String?): com.p4r4d0x.punkapibeerviewer.model.Method? {
        return Gson().fromJson(value, com.p4r4d0x.punkapibeerviewer.model.Method::class.java)
    }

    @TypeConverter
    fun fromMethod(method: com.p4r4d0x.punkapibeerviewer.model.Method?): String? {
        return Gson().toJson(method)
    }

    @TypeConverter
    fun fromStringBoilVolume(value: String?): com.p4r4d0x.punkapibeerviewer.model.BoilVolume? {
        return Gson().fromJson(value, com.p4r4d0x.punkapibeerviewer.model.BoilVolume::class.java)
    }

    @TypeConverter
    fun fromBoilVolume(method: com.p4r4d0x.punkapibeerviewer.model.BoilVolume?): String? {
        return Gson().toJson(method)
    }

    @TypeConverter
    fun fromStringVolume(value: String?): com.p4r4d0x.punkapibeerviewer.model.Volume? {
        return Gson().fromJson(value, com.p4r4d0x.punkapibeerviewer.model.Volume::class.java)
    }

    @TypeConverter
    fun fromVolume(method: com.p4r4d0x.punkapibeerviewer.model.Volume?): String? {
        return Gson().toJson(method)
    }

    @TypeConverter
    fun fromStringIngredients(value: String?): com.p4r4d0x.punkapibeerviewer.model.Ingredients? {
        return Gson().fromJson(value, com.p4r4d0x.punkapibeerviewer.model.Ingredients::class.java)
    }

    @TypeConverter
    fun fromIngredients(method: com.p4r4d0x.punkapibeerviewer.model.Ingredients?): String? {
        return Gson().toJson(method)
    }
}