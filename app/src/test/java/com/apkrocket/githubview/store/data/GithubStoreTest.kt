package com.apkrocket.githubview.store.data

import com.apkrocket.githubview.store.rest.GithubService
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class GithubStoreTest {

    private val mockGithubService = mock(GithubService::class.java)
    lateinit var mockGithubStore: GithubStore

    @Before
    fun setUp() {
        mockGithubStore = GithubStore(mockGithubService)
    }

    @Test
    fun fetchReposAsync() = runBlocking {
        val exception = RuntimeException("Failed")

        Mockito.`when`(mockGithubService.fetchReposAsync()).thenThrow(exception)

        assertEquals(Result.Error(exception) ,mockGithubStore.fetchReposAsync())

    }
}