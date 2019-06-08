package com.apkrocket.githubview.ui.repos

import androidx.lifecycle.Observer
import com.apkrocket.githubview.store.data.GithubStore
import com.apkrocket.githubview.store.rest.GithubService
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposIntent
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposViewState
import com.jraska.livedata.test
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ListReposViewModelTest {

    private val mockGithubService = mock(GithubService::class.java)
    private val mockGithubStore = GithubStore(mockGithubService)

    @Mock
    lateinit var testObserver: Observer<ListReposViewState>

    lateinit var viewModel: ListReposViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        viewModel = ListReposViewModel(mockGithubStore)
    }

    @Test
    fun testFetchSomeRepos() {
//        val capturedStates = ArgumentCaptor.forClass(ListReposViewState::class.java)

        runBlocking {
            `when`(mockGithubService.fetchReposAsync()).thenThrow(RuntimeException("OUCH"))
        }

        viewModel.states()
            .test()
            .assertValue(ListReposViewState.idle())

        viewModel.processIntent(ListReposIntent.InitialIntent)


//        verify(testObserver).onChanged(capturedStates.capture())
//
//        val expectedList = arrayListOf<ListReposViewState>(
//            ListReposViewState.idle(),
//            ListReposViewState.idle().copy(loading = true),
//            ListReposViewState.idle().copy(error = RuntimeException("OUCH"))
//        )
//
//        assert(capturedStates == expectedList)

    }
}