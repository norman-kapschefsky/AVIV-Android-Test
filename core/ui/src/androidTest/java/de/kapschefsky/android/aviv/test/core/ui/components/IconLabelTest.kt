package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.test.R
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import de.kapschefsky.android.aviv.test.core.ui.theme.AppTheme
import org.junit.Rule
import org.junit.Test

class IconLabelTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun iconLabelHasIconAndLabel() {
        val icon = Icons.Filled.Info

        composeTestRule.setContent { AppTheme { IconLabel(icon = icon, label = TEST_LABEL) } }
        composeTestRule.onNodeWithText(TEST_LABEL).assertExists()
        composeTestRule.onNodeWithTag(TAG_ICON_LABEL_ICON).assertExists()
    }

    companion object {
        private const val TEST_LABEL = "myLabel"
    }
}