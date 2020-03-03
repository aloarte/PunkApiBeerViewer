package com.p4r4d0x.punkapibeerviewer.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.p4r4d0x.punkapibeerviewer.utilities.Constants.BEER_DATABASE_NAME
import com.p4r4d0x.punkapibeerviewer.utilities.DatabaseConverters

/**
 * The Room database for this app
 */
@Database(entities = [BeerDTO::class], version = 1, exportSchema = false)
@TypeConverters(DatabaseConverters::class)
abstract class BeerDatabase : RoomDatabase() {
    abstract fun beerDao(): BeerDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: BeerDatabase? = null

        /**
         * Get the instance of the BeerDatabase
         */
        fun getInstance(context: Context): BeerDatabase {
            return instance
                ?: synchronized(this) {
                    instance
                        ?: buildDatabase(
                            context
                        ).also { instance = it }
                }
        }

        /**
         * Create the database without any previous data
         */
        private fun buildDatabase(context: Context): BeerDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                BeerDatabase::class.java,
                BEER_DATABASE_NAME
            ).build()
        }
    }
}
