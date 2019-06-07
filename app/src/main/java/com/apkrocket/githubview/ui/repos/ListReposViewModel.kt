package com.apkrocket.githubview.ui.repos


import android.os.Handler
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apkrocket.githubview.ui.base.mvibase.MviViewModel
import com.apkrocket.githubview.ui.repos.ListReposContract.*
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposAction.FetchSomeReposAction
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposIntent.InitialIntent
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposResult.FetchSomeReposResult
import javax.inject.Inject

class ListReposViewModel @Inject constructor(
//    val githubService: GithubService
) : ViewModel(), MviViewModel<ListReposIntent> {

    private var currentViewState = ListReposViewState.idle()

    private val viewStateObservable = MutableLiveData<ListReposViewState>()

    init {
        viewStateObservable.value = currentViewState
    }

    private fun fetchSomeReposeActionProcessor() {
        reduceStateFromResult(FetchSomeReposResult.InFlight)

        Handler().postDelayed({
            reduceStateFromResult(FetchSomeReposResult.Success(arrayListOf()))
        }, 300)
    }

    override fun processIntent(intent: ListReposIntent) {
        processActions(actionFromIntent(intent))
    }

    private fun actionFromIntent(intent: ListReposIntent): ListReposAction {
        return when (intent) {
            is InitialIntent -> FetchSomeReposAction
        }
    }

    private fun processActions(action: ListReposAction) {
        when (action) {
            is FetchSomeReposAction -> fetchSomeReposeActionProcessor()
        }
    }

    private fun reduceStateFromResult(result: ListReposResult) {
        currentViewState = when (result) {
            is FetchSomeReposResult -> when (result) {
                is FetchSomeReposResult.Success -> currentViewState.copy(
                    loading = false
                )
                is FetchSomeReposResult.Failure -> currentViewState.copy(

                )
                is FetchSomeReposResult.InFlight -> currentViewState.copy(
                    loading = true
                )
            }
        }

        viewStateObservable.value = currentViewState
    }

    fun states() = viewStateObservable
}