package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import de.kapschefsky.android.aviv.test.core.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test

class LoadingIndicatorTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loadingIndicatorIsPresent() {
        composeTestRule.setContent { AppTheme { LoadingIndicator() } }
        composeTestRule.onNodeWithTag(TAG_LOADING_INDICATOR).assertExists()
    }
}