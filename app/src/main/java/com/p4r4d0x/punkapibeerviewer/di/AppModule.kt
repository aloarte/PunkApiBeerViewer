package com.p4r4d0x.punkapibeerviewer.di

import android.content.Context
import com.p4r4d0x.punkapibeerviewer.PunkapiBeerViewerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: PunkapiBeerViewerApplication) {
    @Provides
    @Singleton
    fun provideContext(): Context = application
}