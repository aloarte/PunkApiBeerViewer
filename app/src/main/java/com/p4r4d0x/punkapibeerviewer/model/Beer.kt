package com.p4r4d0x.punkapibeerviewer.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "beer_table")
data class Beer(

    @PrimaryKey
    @SerializedName("id")
    val id: Int = 0,

    val name: String = ""
)