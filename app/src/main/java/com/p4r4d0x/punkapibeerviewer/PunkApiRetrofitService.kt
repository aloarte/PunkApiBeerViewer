package com.p4r4d0x.punkapibeerviewer

import com.p4r4d0x.punkapibeerviewer.model.BeerDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PunkApiRetrofitService {

    /**
     * Get a random beer returned into a List<BeerDto>
     */
    @GET("v2/beers/random")
    fun getRandomBeer(): Call<List<BeerDTO?>?>?

    /**
     * Get the beers that match with the name pased. The results are returned in a list of BeerDTO
     */
    @GET("/v2/beers")
    fun getBeerByName(@Query("beer_name") beerName: String?): Call<List<BeerDTO?>?>?
}