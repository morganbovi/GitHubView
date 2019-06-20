package com.apkrocket.githubview.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executors

@ExperimentalCoroutinesApi
open class BaseJunitTest {

    @Rule
    @JvmField
    var rule: TestRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = Executors.newSingleThreadExecutor().asCoroutineDispatcher()

    @Before
    open fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    open fun cleanUp() {
        mainThreadSurrogate.close()
    }
}