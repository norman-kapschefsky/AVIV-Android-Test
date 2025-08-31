package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import de.kapschefsky.android.aviv.test.core.ui.theme.ThemeDarkPreview
import de.kapschefsky.android.aviv.test.core.ui.theme.ThemeLightPreview

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    indicatorModifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = indicatorModifier,
            color = MaterialTheme.colorScheme.primary
        )
    }
}

@ThemeLightPreview
@ThemeDarkPreview
@Composable
internal fun LoadingIndicatorPreview() {
    LoadingIndicator(
        modifier = Modifier.fillMaxWidth().height(100.dp),
        indicatorModifier = Modifier.size(48.dp)
    )
}
