package com.p4r4d0x.punkapibeerviewer

import android.util.Log
import com.p4r4d0x.punkapibeerviewer.model.*


class BeerRepository constructor(
    private val beerDao: BeerDao,
    private val webservice: PunkApiRetrofitService
) {

    fun getBeers() = beerDao.getBeers()

    suspend fun getBeers(beerName: String): List<BeerDTO> {
        //Try to get the beers from the local database
        val beers = beerDao.load("$beerName%")
        Log.d("ALRALR", "Recovered ${beers.size} beers from database with $beerName%")

        //If there is not any list of beers matching the beer name, request to the service
        return if (beers.isEmpty()) {
            requestBeers(beerName)
        } else beers

    }

    private suspend fun requestBeers(beerName: String): List<BeerDTO> {

        Log.d("ALRALR", "requestBeersFrom backend")
        //val beerList = getBeer(beerName)
        //beerDao.save(beerList)
        //return beerList


        val response = webservice.getBeerByName(beerName)!!.execute()


        beerDao.save(response.body()!!)
        return response.body() as List<BeerDTO>


    }

    fun getInitBeerRepositoryData(): List<BeerDTO> {
        return ArrayList<BeerDTO>()
    }

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: BeerRepository? = null

        fun getInstance(beerDao: BeerDao, punkApiRetrofitService: PunkApiRetrofitService) =
            instance ?: synchronized(this) {
                instance ?: BeerRepository(beerDao, punkApiRetrofitService).also { instance = it }
            }
    }

//    fun getBeers(beerName: String) : List<BeerDTO>{
//
//        var beerList: ArrayList<BeerDTO> = ArrayList()
//
//        beerList.add(BeerDTO("Brewed",83.0, Method(null,Fermentation(Temp("",14)),""),1040.0,"imageurl", BoilVolume("litres",20),8.0,"description",9.9,4.5,
//            Volume(),"",4.1,null,"",4.1,"",
//            Ingredients(null,"",null),3,3.4,""))
//        beerList.add(BeerDTO("Brewed",83.0, Method(null,Fermentation(Temp("",14)),""),1040.0,"imageurl", BoilVolume("litres",20),8.0,"description",9.9,4.5,
//            Volume(),"",4.1,null,"",4.1,"",
//            Ingredients(null,"",null),3,3.4,""))
//
//        return beerList
//
//
//    }

    private fun getBeer(beerName: String): List<BeerDTO> {

        /*val beerList: ArrayList<Beer> = ArrayList()
        beerList.add(Beer(1,"aa"))
        beerList.add(Beer(2,"ss"))
*/
        val beerList: ArrayList<BeerDTO> = ArrayList()
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
                3,
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



        return beerList


    }


}