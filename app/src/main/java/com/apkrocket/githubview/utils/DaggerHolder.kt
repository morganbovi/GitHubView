package com.apkrocket.githubview.utils

import com.apkrocket.githubview.BaseApplication
import com.apkrocket.githubview.di.AppComponent

class DaggerHolder constructor() {
    companion object {
        fun getComponent(): AppComponent =
            BaseApplication.get().component
    }
}