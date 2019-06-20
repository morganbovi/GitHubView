package com.apkrocket.githubview.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class TestLifecycle : LifecycleOwner {

    private val registry = LifecycleRegistry(this)

    val currentState: Lifecycle.State
        get() = registry.currentState

    fun create(): TestLifecycle {
        return handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
    }

    fun start(): TestLifecycle {
        return handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    fun resume(): TestLifecycle {
        return handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    fun pause(): TestLifecycle {
        return handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    }

    fun stop(): TestLifecycle {
        return handleLifecycleEvent(Lifecycle.Event.ON_STOP)
    }

    fun destroy(): TestLifecycle {
        return handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    private fun handleLifecycleEvent(event: Lifecycle.Event): TestLifecycle {
        registry.handleLifecycleEvent(event)
        return this
    }

    override fun getLifecycle(): Lifecycle {
        return registry
    }

    companion object {

        fun initialized(): TestLifecycle {
            return TestLifecycle()
        }

        fun resumed(): TestLifecycle {
            return initialized().resume()
        }
    }
}