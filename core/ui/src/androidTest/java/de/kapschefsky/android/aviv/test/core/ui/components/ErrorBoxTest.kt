package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import de.kapschefsky.android.aviv.test.core.ui.theme.AppTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ErrorBoxTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun errorBoxHasTextAndNoButton() {
        composeTestRule.setContent { AppTheme { ErrorBox(text = TEST_TEXT) } }
        composeTestRule.onNodeWithText(TEST_TEXT).assertExists()
        composeTestRule.onNodeWithTag(TAG_ERROR_BOX_BUTTON).assertDoesNotExist()
    }

    @Test
    fun errorBoxHasTextAndButton() {
        var buttonClicked = false

        composeTestRule.setContent {
            AppTheme {
                ErrorBox(
                    text = TEST_TEXT,
                    buttonLabel = TEST_BUTTON_LABEL,
                    onButtonClicked = { buttonClicked = true }
                )
            }
        }

        composeTestRule.onNodeWithTag(TAG_ERROR_BOX_TEXT).assertExists()
        composeTestRule.onNodeWithTag(TAG_ERROR_BOX_TEXT).assertTextEquals(TEST_TEXT)

        composeTestRule.onNodeWithTag(TAG_ERROR_BOX_BUTTON).assertExists()
        composeTestRule.onNodeWithText(TEST_BUTTON_LABEL).assertExists()

        composeTestRule.onNodeWithTag(TAG_ERROR_BOX_BUTTON).performClick()
        assertTrue(buttonClicked)
    }

    companion object {
        private const val TEST_TEXT = "myText"
        private const val TEST_BUTTON_LABEL = "myButtonLabel"
    }
}
