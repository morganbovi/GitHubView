package com.apkrocket.githubview.di.app

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ContextModule(private val mContext: Context) {

    @Provides
    internal fun provideContext(): Context {
        return mContext
    }

}