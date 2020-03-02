package com.p4r4d0x.punkapibeerviewer

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.p4r4d0x.punkapibeerviewer.model.*
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class EntityTest {
    private lateinit var beerDao: BeerDao
    private lateinit var db: BeerDatabase
    lateinit var beerList: ArrayList<BeerDTO>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {

        beerList = ArrayList()

        beerList.add(
            BeerDTO(
                "Brewed",
                83.0,
                Method(null, Fermentation(Temp("", 14)), ""),
                1040.0,
                "imageurl",
                BoilVolume("litres", 20),
                8.0,
                "description",
                9.9,
                4.5,
                Volume(),
                "",
                4.1,
                null,
                "punkipa",
                4.1,
                "",
                Ingredients(null, "", null),
                4,
                3.4,
                ""
            )
        )
        beerList.add(
            BeerDTO(
                "Brewed",
                83.0,
                Method(null, Fermentation(Temp("", 14)), ""),
                1040.0,
                "imageurl",
                BoilVolume("litres", 20),
                8.0,
                "description",
                9.9,
                4.5,
                Volume(),
                "",
                4.1,
                null,
                "punk",
                4.1,
                "",
                Ingredients(null, "", null),
                3,
                3.4,
                ""
            )
        )


        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, BeerDatabase::class.java
        ).build()
        beerDao = db.beerDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() = runBlocking {

        beerDao.save(beerList)

        val wholeDb = beerDao.getBeers()
        assertNotNull(wholeDb)
        var byName = beerDao.load("punk%")
        assertEquals(wholeDb.size, byName.size)

        byName = beerDao.load("punkipa%")
        assertEquals(wholeDb.size, byName.size + 1)

    }
}
