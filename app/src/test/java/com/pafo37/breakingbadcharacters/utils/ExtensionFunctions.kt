package com.pafo37.breakingbadcharacters.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock

fun <T> LiveData<T>.withObserver(block: (Observer<T>) -> Unit) {
    val observer: Observer<T> = mock()
    observeForever(observer)
    block(observer)
    removeObserver(observer)
}