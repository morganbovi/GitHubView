package com.apkrocket.githubview.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

class TestObserver<T> : Observer<T> {

    val values = mutableListOf<T>()

    override fun onChanged(value: T) {
        values.add(value)
    }
}

fun <T> LiveData<T>.test(): TestObserver<T> {
    val observer = TestObserver<T>()
    this.observeForever(observer)
    return observer
}