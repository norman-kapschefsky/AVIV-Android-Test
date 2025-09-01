package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import de.kapschefsky.android.aviv.test.core.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test

class HeadlineTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun headlineHasText() {
        composeTestRule.setContent { AppTheme { Headline(text = TEST_TEXT) } }
        composeTestRule.onNodeWithText(TEST_TEXT).assertExists()
    }

    companion object {
        private const val TEST_TEXT = "myText"
    }
}