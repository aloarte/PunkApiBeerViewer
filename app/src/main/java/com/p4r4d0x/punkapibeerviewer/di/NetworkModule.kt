package com.p4r4d0x.punkapibeerviewer.di

import com.p4r4d0x.punkapibeerviewer.model.PunkApiRetrofitService
import com.p4r4d0x.punkapibeerviewer.utilities.Constants
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun providePunkApiRetrofitService(): PunkApiRetrofitService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .baseUrl(Constants.PUNK_API_BASE_URL)
            .build()

        return retrofit.create(PunkApiRetrofitService::class.java)
    }
}