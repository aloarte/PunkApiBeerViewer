package com.p4r4d0x.punkapibeerviewer.model

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BeerRepository @Inject constructor(
    private val beerDao: BeerDao,
    private val webservice: PunkApiRetrofitService

) {

    suspend fun deleteBeers() = beerDao.removeAllBeers()

    /**
     * Get a list of beers that its name starts by $beerName
     */
    suspend fun getBeers(beerName: String): List<BeerDTO> {
        //Try to get the beers from the local database
        val beers = beerDao.getBeersByName("%$beerName%")
        Log.d("BeerRepository", "Recovered ${beers.size} beers from database with \"$beerName\"")

        return if (beers.isEmpty()) {
            //If there is not any list of beers matching the beer name, request from the service
            requestBeers(beerName)
        } else beers

    }

    /**
     * Return a list of beers that its name starts by $beerName obtained from the punk api service
     */
    private suspend fun requestBeers(beerName: String): List<BeerDTO> {

        Log.d("BeerRepository", "Requesting beers from backend with name: $beerName")
        //Request the beers from the service
        val response = webservice.getBeerByName(beerName)!!.execute()
        Log.d(
            "BeerRepository",
            "Result from backend with name: $beerName: ${response.body()?.size}"
        )

        //Save the new data into the database
        beerDao.saveBeers(response.body()!!)

        //Return the list of beers
        return response.body() as List<BeerDTO>
    }

}