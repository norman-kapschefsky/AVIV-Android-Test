package de.kapschefsky.android.aviv.test.core.domain.common

import kotlinx.coroutines.Dispatchers
import org.junit.Test
import org.junit.Assert.assertEquals

class CoroutineDispatcherProviderTest {

    @Test
    fun `Coroutine dispatchers are properly defined`() {
        assertEquals(Dispatchers.Main, defaultCoroutineDispatcherProvider.main)
        assertEquals(Dispatchers.IO, defaultCoroutineDispatcherProvider.io)
    }
}