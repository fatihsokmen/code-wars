package com.github.fatihsokmen.codewars

import android.app.Application
import com.facebook.stetho.Stetho
import com.github.fatihsokmen.codewars.dependency.BaseComponent
import com.github.fatihsokmen.codewars.dependency.DaggerAppComponent

class App : Application() {

    lateinit var baseComponent: BaseComponent

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        baseComponent = DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }
}
