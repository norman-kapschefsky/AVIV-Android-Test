package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import de.kapschefsky.android.aviv.test.core.ui.theme.ThemeDarkPreview
import de.kapschefsky.android.aviv.test.core.ui.theme.ThemeLightPreview

@Composable
fun ErrorBox(
    text: String,
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit = {
        Icon(
            imageVector = Icons.Filled.Error,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onError,
        )
    },
    buttonLabel: String? = null,
    onButtonClicked: () -> Unit = {},
) {
    Column(
        modifier =
            modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(32.dp))
                .background(MaterialTheme.colorScheme.error)
                .padding(16.dp),
        horizontalAlignment = Alignment.End,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            icon()

            Text(
                text = text,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onError,
                modifier = Modifier.padding(start = 8.dp),
            )
        }

        buttonLabel?.let { label ->
            Button(
                onClick = onButtonClicked,
            ) {
                Text(text = label)
            }
        }
    }
}

@ThemeLightPreview
@ThemeDarkPreview
@Composable
internal fun ErrorBoxPreview() {
    ErrorBox(
        text = LoremIpsum(20).values.joinToString(),
        buttonLabel = LoremIpsum(1).values.first(),
    )
}
