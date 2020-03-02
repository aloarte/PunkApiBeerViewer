package com.p4r4d0x.punkapibeerviewer

import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InjectorUtils {

    fun provideBeerListViewModelFactory(context: Context): BeerViewModelFactory {
        val repository = getBeerRepository(context)
        return BeerViewModelFactory(repository)
    }

    private fun getBeerRepository(context: Context): BeerRepository {
        return BeerRepository.getInstance(
            BeerDatabase.getInstance(context.applicationContext).beerDao(),
            getPunkApiRetrofitService()
        )


    }

    private fun getPunkApiRetrofitService(): PunkApiRetrofitService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl("https://api.punkapi.com/")
            .build()

        return retrofit.create(PunkApiRetrofitService::class.java)
    }
}