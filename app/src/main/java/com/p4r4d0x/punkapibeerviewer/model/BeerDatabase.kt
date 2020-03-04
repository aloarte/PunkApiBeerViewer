package com.p4r4d0x.punkapibeerviewer.model

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.p4r4d0x.punkapibeerviewer.utilities.DatabaseConverters

/**
 * The Room database for this app
 */
@Database(entities = [BeerDTO::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao

}
