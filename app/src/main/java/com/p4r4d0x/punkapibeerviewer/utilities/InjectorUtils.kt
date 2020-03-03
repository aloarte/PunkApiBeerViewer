package com.p4r4d0x.punkapibeerviewer.utilities

import android.content.Context
import com.p4r4d0x.punkapibeerviewer.model.BeerDatabase
import com.p4r4d0x.punkapibeerviewer.model.BeerRepository
import com.p4r4d0x.punkapibeerviewer.model.PunkApiRetrofitService
import com.p4r4d0x.punkapibeerviewer.utilities.Constants.PUNK_API_BASE_URL
import com.p4r4d0x.punkapibeerviewer.viewmodel.BeerViewModelFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object InjectorUtils {

    fun provideBeerListViewModelFactory(context: Context): BeerViewModelFactory {
        //Get the repository
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
            .baseUrl(PUNK_API_BASE_URL)
            .build()

        return retrofit.create(PunkApiRetrofitService::class.java)
    }
}