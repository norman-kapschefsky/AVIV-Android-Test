package de.kapschefsky.android.aviv.test.core.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

@Composable
fun Modifier.clickableRipple(
    enabled: Boolean = true,
    onClick: (() -> Unit)?
): Modifier =
    if (onClick == null) {
        this
    } else {
        composed {
            clickable(
                onClick = onClick,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(),
                enabled = enabled
            )
        }
    }
