package com.p4r4d0x.punkapibeerviewer

import android.app.Application
import com.p4r4d0x.punkapibeerviewer.di.AppModule
import com.p4r4d0x.punkapibeerviewer.di.ApplicationComponent
import com.p4r4d0x.punkapibeerviewer.di.DaggerApplicationComponent


class PunkapiBeerViewerApplication : Application() {
    val appComponent: ApplicationComponent = DaggerApplicationComponent.builder().appModule(
        AppModule(this)
    ).build()
}