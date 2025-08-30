package de.kapschefsky.android.aviv.test.core.domain.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface CoroutineDispatcherProvider {
    val main: CoroutineDispatcher
    val io: CoroutineDispatcher
}

internal val defaultCoroutineDispatcherProvider : CoroutineDispatcherProvider =
    object : CoroutineDispatcherProvider {
        override val main: CoroutineDispatcher
            get() = Dispatchers.Main

        override val io: CoroutineDispatcher
            get() = Dispatchers.IO
    }