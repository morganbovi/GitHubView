package com.apkrocket.githubview.ui.base.mvibase

import androidx.lifecycle.LiveData

interface MviViewModel<I : MviIntent, S : MviViewState> {

    fun processIntent(intent: I)

    fun states(): LiveData<S>

}