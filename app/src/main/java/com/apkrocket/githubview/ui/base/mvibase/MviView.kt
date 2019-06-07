package com.apkrocket.githubview.ui.base.mvibase

interface MviView<S : MviViewState> {

    fun render(state: S)

}