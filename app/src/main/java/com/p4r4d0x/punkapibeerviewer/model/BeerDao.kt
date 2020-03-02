package com.p4r4d0x.punkapibeerviewer.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BeerDao {

    @Query("SELECT * FROM beer_table ORDER BY name")
    fun getBeers(): List<BeerDTO>

    @Query("SELECT * FROM beer_table WHERE name LIKE :beerName")
    fun load(beerName: String): List<BeerDTO>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(beer: List<BeerDTO>)

}
