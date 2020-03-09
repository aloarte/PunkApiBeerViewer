package com.p4r4d0x.punkapibeerviewer.di

import com.p4r4d0x.punkapibeerviewer.DetailsFragment
import com.p4r4d0x.punkapibeerviewer.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class, AppModule::class])
interface ApplicationComponent {
    fun inject(fragment: SearchFragment)
    fun inject(fragment: DetailsFragment)
}
