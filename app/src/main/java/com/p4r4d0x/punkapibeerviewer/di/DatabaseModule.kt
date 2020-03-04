package com.p4r4d0x.punkapibeerviewer.di

import android.content.Context
import androidx.room.Room
import com.p4r4d0x.punkapibeerviewer.model.BeerDao
import com.p4r4d0x.punkapibeerviewer.model.BeerDatabase
import com.p4r4d0x.punkapibeerviewer.utilities.Constants.BEER_DATABASE_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideBeerDao(beerDatabase: BeerDatabase): BeerDao {
        return beerDatabase.beerDao()
    }

    @Singleton
    @Provides
    fun provideBeerDatabase(context: Context): BeerDatabase {
        return synchronized(this) {
            Room.databaseBuilder(
                context.applicationContext,
                BeerDatabase::class.java,
                BEER_DATABASE_NAME
            ).build()
        }
    }


}