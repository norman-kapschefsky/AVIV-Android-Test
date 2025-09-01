package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import de.kapschefsky.android.aviv.test.core.ui.theme.ThemeDarkPreview
import de.kapschefsky.android.aviv.test.core.ui.theme.ThemeLightPreview

@Composable
fun IconLabel(
    icon: ImageVector,
    label: String,
    modifier: Modifier = Modifier,
    iconSize: Dp = 24.dp,
    iconTintColor: Color = MaterialTheme.colorScheme.onSurface,
    labelColor: Color = MaterialTheme.colorScheme.onSurface,
    labelStyle: TextStyle = MaterialTheme.typography.bodyMedium,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(iconSize).testTag(TAG_ICON_LABEL_ICON),
            imageVector = icon,
            contentDescription = null,
            tint = iconTintColor,
        )

        Text(
            text = label,
            modifier = Modifier.padding(start = 8.dp).testTag(TAG_ICON_LABEL_TEXT),
            color = labelColor,
            style = labelStyle,
        )
    }
}

@ThemeLightPreview
@ThemeDarkPreview
@Composable
internal fun IconLabelPreview() {
    IconLabel(
        icon = Icons.Filled.Phone,
        label = LoremIpsum(20).values.joinToString(),
    )
}

const val TAG_ICON_LABEL_ICON = "TAG_ICON_LABEL_ICON"
const val TAG_ICON_LABEL_TEXT = "TAG_ICON_LABEL_TEXT"
