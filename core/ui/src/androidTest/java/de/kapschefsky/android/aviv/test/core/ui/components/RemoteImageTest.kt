package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import de.kapschefsky.android.aviv.test.core.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test

class RemoteImageTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun remoteImageHasFallbackIfUrlIsNotLoadable() {
        composeTestRule.setContent {
            AppTheme {
                RemoteImage(
                    imageUrl = null,
                    fallbackImage = { Text(TEST_FALLBACK_TEXT) }
                )
            }
        }

        composeTestRule.onNodeWithText(TEST_FALLBACK_TEXT).assertExists()
    }

    companion object {
        private const val TEST_FALLBACK_TEXT = "myImageFallbackText"
    }
}