package de.kapschefsky.android.aviv.test.core.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.House
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import de.kapschefsky.android.aviv.test.core.ui.theme.ThemeDarkPreview
import de.kapschefsky.android.aviv.test.core.ui.theme.ThemeLightPreview

@Composable
fun RemoteImage(
    imageUrl: String?,
    modifier: Modifier = Modifier,
    fallbackImage: @Composable () -> Unit = {
        Image(
            modifier = Modifier.size(64.dp),
            imageVector = Icons.Filled.House,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurfaceVariant),
        )
    },
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        val painter = rememberAsyncImagePainter(imageUrl)
        val state by painter.state.collectAsState()

        when (state) {
            is AsyncImagePainter.State.Empty,
            is AsyncImagePainter.State.Loading,
            -> LoadingIndicator()

            is AsyncImagePainter.State.Success ->
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )

            is AsyncImagePainter.State.Error -> fallbackImage()
        }
    }
}

@ThemeLightPreview
@ThemeDarkPreview
@Composable
internal fun RemoteImagePreview() {
    RemoteImage(imageUrl = null)
}
