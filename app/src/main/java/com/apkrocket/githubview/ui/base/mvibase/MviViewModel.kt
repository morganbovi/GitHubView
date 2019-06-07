package com.apkrocket.githubview.ui.base.mvibase

interface MviViewModel<I : MviIntent> {

    fun processIntent(intent: I)

}