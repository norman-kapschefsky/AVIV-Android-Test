package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import de.kapschefsky.android.aviv.test.core.ui.theme.ThemeDarkPreview
import de.kapschefsky.android.aviv.test.core.ui.theme.ThemeLightPreview

@Composable
fun Headline(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.testTag(TAG_HEADLINE),
        text = text,
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.primary,
    )
}

@ThemeLightPreview
@ThemeDarkPreview
@Composable
internal fun HeadlinePreview() {
    Headline(
        text = LoremIpsum(20).values.joinToString(),
        modifier = Modifier.padding(16.dp)
    )
}

const val TAG_HEADLINE = "TAG_HEADLINE"


