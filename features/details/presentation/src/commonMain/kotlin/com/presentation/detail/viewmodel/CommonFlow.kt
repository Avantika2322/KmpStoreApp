package com.presentation.detail.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CommonFlow<T>(
    private val origin: Flow<T>
) {

    fun watch(block: (T) -> Unit): Closeable {
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            origin.collect {
                block(it)
            }
        }

        return object : Closeable {
            override fun close() {
                job.cancel()
            }
        }
    }
}

interface Closeable {
    fun close()
}


fun <T> Flow<T>.asCommonFlow() = CommonFlow(this)