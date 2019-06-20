package com.apkrocket.githubview.ui.repos

import com.apkrocket.githubview.base.BaseJunitTest
import com.apkrocket.githubview.store.data.GithubStore
import com.apkrocket.githubview.store.data.Result
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposIntent
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposViewState
import com.apkrocket.githubview.utils.TestObserver
import com.apkrocket.githubview.utils.test
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class ListReposViewModelTest : BaseJunitTest() {

    private val mockGithubStore = mock(GithubStore::class.java)

    private lateinit var testStatesObserver: TestObserver<ListReposViewState>

    lateinit var viewModel: ListReposViewModel

    @Before
    override fun setUp() {
        super.setUp()

        viewModel = ListReposViewModel(mockGithubStore)
        testStatesObserver = viewModel.states().test()

    }

    @Test
    fun testFetchSomeRepos() = runBlocking {
        val exception = RuntimeException("OUCH")

        Mockito.`when`(mockGithubStore.fetchReposAsync()).thenReturn(Result.Error(exception))

        viewModel.processIntent(ListReposIntent.InitialIntent)

        verify(mockGithubStore, times(1)).fetchReposAsync()

        assertEquals(ListReposViewState.idle(), testStatesObserver.values[0])
        assertEquals(ListReposViewState.idle().copy(loading = true), testStatesObserver.values[1])
        assertEquals(ListReposViewState.idle().copy(error = exception), testStatesObserver.values[2])

    }
}