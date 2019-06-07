package com.apkrocket.githubview.di

import com.apkrocket.githubview.di.app.ContextModule
import com.apkrocket.githubview.di.app.DataModule
import com.apkrocket.githubview.di.app.PreferenceModule
import com.apkrocket.githubview.store.PreferenceStore
import com.apkrocket.githubview.ui.repos.ListReposActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        ContextModule::class,
        DataModule::class,
        PreferenceModule::class
    ]
)
interface AppComponent {

    fun getPreferenceStore(): PreferenceStore

    fun inject(obj: ListReposActivity)

}