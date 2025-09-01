package de.kapschefsky.android.aviv.test

import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test

class AppTest {
    @Test
    fun properPackageName() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("de.kapschefsky.android.aviv.test", appContext.packageName)
    }
}
