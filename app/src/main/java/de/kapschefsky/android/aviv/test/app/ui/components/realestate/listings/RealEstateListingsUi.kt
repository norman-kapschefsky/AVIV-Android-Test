package de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bed
import androidx.compose.material.icons.filled.EuroSymbol
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MeetingRoom
import androidx.compose.material.icons.filled.Square
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import de.kapschefsky.android.aviv.test.app.ui.components.common.RemoteImage
import de.kapschefsky.android.aviv.test.core.model.RealEstateId
import de.kapschefsky.android.aviv.test.core.ui.components.LoadingIndicator
import de.kapschefsky.android.aviv.test.core.ui.theme.clickableRipple

@Composable
fun RealEstateListingsUi(
    modifier: Modifier = Modifier,
    viewModel: RealEstateListingsViewModel = hiltViewModel(),
    onRealEstateItemClicked: (RealEstateId) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRealEstateListings()
    }

    when (val state = uiState.value) {
        RealEstateListingsUiState.Loading -> LoadingUi(modifier = modifier)
        is RealEstateListingsUiState.RealEstateListings ->
            ListingsUi(
                items = state.items,
                onRealEstateItemClicked = onRealEstateItemClicked,
                modifier = modifier,
            )

        is RealEstateListingsUiState.Error.Loading -> LoadingErrorUi(modifier = modifier)
    }
}

@Composable
private fun LoadingUi(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        LoadingIndicator()
    }
}

@Composable
private fun ListingsUi(
    items: List<RealEstateListItemUiModel>,
    onRealEstateItemClicked: (RealEstateId) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
    ) {
        val cornerShape = RoundedCornerShape(size = 32.dp)

        items(
            items = items,
            key = { item -> item.id },
        ) { item ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .requiredHeight(164.dp)
                        .clip(cornerShape)
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .clickableRipple { onRealEstateItemClicked(item.id) },
            ) {
                RemoteImage(
                    imageUrl = item.url,
                    modifier =
                        Modifier
                            .width(164.dp)
                            .fillMaxHeight()
                            .clip(cornerShape)
                            .background(MaterialTheme.colorScheme.surfaceDim),
                )

                Column(
                    modifier = Modifier.fillMaxHeight().padding(start = 16.dp),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        modifier = Modifier.padding(bottom = 4.dp),
                    )

                    Info(icon = Icons.Filled.LocationOn, label = item.city)
                    Info(icon = Icons.Filled.Square, label = item.area.toString() + "m²")
                    Info(icon = Icons.Filled.EuroSymbol, label = item.price.toString() + "€")

                    item.rooms?.let {
                        Info(icon = Icons.Filled.MeetingRoom, label = it.toString())
                    }

                    item.bedrooms?.let {
                        Info(icon = Icons.Filled.Bed, label = it.toString())
                    }
                }
            }

            if (items.lastOrNull() != item) {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun Info(
    icon: ImageVector,
    label: String,
) {
    Row {
        Icon(
            imageVector = icon,
            contentDescription = null,
        )

        Text(
            text = label,
            modifier = Modifier.padding(start = 8.dp),
        )
    }
}

@Composable
private fun LoadingErrorUi(modifier: Modifier = Modifier) {
    Text("Error!")
}
