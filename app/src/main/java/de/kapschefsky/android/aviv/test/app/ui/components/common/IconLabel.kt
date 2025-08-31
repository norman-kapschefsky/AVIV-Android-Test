package de.kapschefsky.android.aviv.test.app.ui.components.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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
            modifier = Modifier.size(iconSize),
            imageVector = icon,
            contentDescription = null,
            tint = iconTintColor,
        )

        Text(
            text = label,
            modifier = Modifier.padding(start = 8.dp),
            color = labelColor,
            style = labelStyle,
        )
    }
}
