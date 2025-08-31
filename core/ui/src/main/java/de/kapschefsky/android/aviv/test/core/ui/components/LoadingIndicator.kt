package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoadingIndicator(
    modifier: Modifier = Modifier,
    containerModifier: Modifier = Modifier
) {
    Box(
        modifier = containerModifier,
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = modifier,
            color = MaterialTheme.colorScheme.primary
        )
    }
}