package com.apkrocket.githubview.di.app

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.apkrocket.githubview.di.app.ContextModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [ContextModule::class])
class PreferenceModule {

    @Singleton
    @Provides
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(context)
    }
}
