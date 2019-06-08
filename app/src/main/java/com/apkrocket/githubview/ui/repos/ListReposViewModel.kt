package com.apkrocket.githubview.ui.repos


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.apkrocket.githubview.store.data.GithubStore
import com.apkrocket.githubview.store.data.Result
import com.apkrocket.githubview.ui.base.mvibase.MviViewModel
import com.apkrocket.githubview.ui.repos.ListReposContract.*
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposAction.FetchSomeReposAction
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposIntent.InitialIntent
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposResult.FetchSomeReposResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class ListReposViewModel @Inject constructor(
    private val githubStore: GithubStore
) : ViewModel(), MviViewModel<ListReposIntent, ListReposViewState> {

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    private var currentViewState = ListReposViewState.idle()

    private val viewStateObservable = MutableLiveData<ListReposViewState>()

    init {
        viewStateObservable.value = currentViewState
    }

    private fun fetchSomeReposeActionProcessor() {
        reduceStateFromResult(FetchSomeReposResult.InFlight)

        uiScope.launch {
            reduceStateFromResult(
                when (val result = githubStore.fetchReposAsync()) {
                    is Result.Success -> {
                        FetchSomeReposResult.Success(result.data)
                    }
                    is Result.Error -> {
                        FetchSomeReposResult.Failure(result.exception)
                    }
                }
            )
        }
    }

    override fun processIntent(intent: ListReposIntent) {
        processActions(actionFromIntent(intent))
    }

    override fun states(): LiveData<ListReposViewState> = viewStateObservable

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
                    loading = false,
                    repos = result.repos
                )
                is FetchSomeReposResult.Failure -> currentViewState.copy(
                    loading = false,
                    error = result.error
                )
                is FetchSomeReposResult.InFlight -> currentViewState.copy(
                    loading = true
                )
            }
        }

        viewStateObservable.value = currentViewState
    }

    override fun onCleared() {
        super.onCleared()
        Timber.e("On Cleared")
    }

}