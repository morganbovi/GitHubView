package com.apkrocket.githubview

import android.app.Application
import com.apkrocket.githubview.di.AppComponent
import com.apkrocket.githubview.di.DaggerAppComponent
import com.apkrocket.githubview.di.app.ContextModule
import timber.log.Timber

class BaseApplication : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        component = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()

    }

    companion object {
        private var INSTANCE: BaseApplication? = null

        @JvmStatic
        fun get(): BaseApplication = INSTANCE!!
    }
}