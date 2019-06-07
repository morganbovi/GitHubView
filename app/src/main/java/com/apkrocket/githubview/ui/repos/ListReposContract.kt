package com.apkrocket.githubview.ui.repos

import com.apkrocket.githubview.model.Repo
import com.apkrocket.githubview.ui.base.mvibase.MviAction
import com.apkrocket.githubview.ui.base.mvibase.MviIntent
import com.apkrocket.githubview.ui.base.mvibase.MviViewState

sealed class ListReposContract {

    sealed class ListReposIntent : MviIntent {
        object InitialIntent : ListReposIntent()
    }

    sealed class ListReposAction : MviAction {
        object FetchSomeReposAction : ListReposAction()
    }

    sealed class ListReposResult : MviAction {
        sealed class FetchSomeReposResult : ListReposResult() {
            data class Success(val repos: List<Repo>) : FetchSomeReposResult()
            data class Failure(val error: Throwable) : FetchSomeReposResult()
            object InFlight : FetchSomeReposResult()
        }
    }

    data class ListReposViewState(
        val loading: Boolean,
        val repos: List<Repo>,
        val error: Throwable?
    ) : MviViewState {
        companion object {
            fun idle() = ListReposViewState(
                loading = false,
                repos = arrayListOf(),
                error = null
            )
        }
    }

}