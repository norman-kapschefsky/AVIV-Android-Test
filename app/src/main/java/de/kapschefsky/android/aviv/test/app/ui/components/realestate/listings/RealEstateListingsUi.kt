package de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import de.kapschefsky.android.aviv.test.R
import de.kapschefsky.android.aviv.test.app.ui.components.common.ErrorInfoBox
import de.kapschefsky.android.aviv.test.app.ui.components.common.IconLabel
import de.kapschefsky.android.aviv.test.app.ui.components.common.RemoteImage
import de.kapschefsky.android.aviv.test.core.ui.components.Headline
import de.kapschefsky.android.aviv.test.core.ui.components.LoadingIndicator
import de.kapschefsky.android.aviv.test.core.ui.theme.clickableRipple

@Composable
fun RealEstateListingsUi(
    modifier: Modifier = Modifier,
    viewModel: RealEstateListingsViewModel = hiltViewModel(),
    onRealEstateItemClicked: (RealEstateListItemUiModel) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRealEstateListings()
    }

    Column(modifier = modifier.background(MaterialTheme.colorScheme.surface)) {
        Headline(
            modifier = Modifier.padding(16.dp),
            text = stringResource(R.string.real_estate_listings_headline),
        )

        when (val state = uiState.value) {
            RealEstateListingsUiState.Loading ->
                LoadingIndicator(containerModifier = Modifier.fillMaxSize())

            is RealEstateListingsUiState.RealEstateListings ->
                ListingsUi(
                    items = state.items,
                    onRealEstateItemClicked = onRealEstateItemClicked,
                )

            is RealEstateListingsUiState.Error.Loading ->
                ErrorInfoBox(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(R.string.real_estate_listings_error_loading_general),
                    buttonLabel = stringResource(R.string.button_error_retry),
                    onButtonClicked = { viewModel.loadRealEstateListings() },
                )
        }
    }
}

@Composable
private fun ListingsUi(
    items: List<RealEstateListItemUiModel>,
    onRealEstateItemClicked: (RealEstateListItemUiModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        items(
            items = items,
            key = { item -> item.id },
        ) { item ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .requiredHeight(164.dp)
                        .clip(RoundedCornerShape(size = 32.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer)
                        .clickableRipple { onRealEstateItemClicked(item) },
            ) {
                RemoteImage(
                    imageUrl = item.url,
                    modifier =
                        Modifier
                            .width(164.dp)
                            .fillMaxHeight()
                            .clip(RoundedCornerShape(topStart = 32.dp, bottomStart = 32.dp))
                            .background(MaterialTheme.colorScheme.secondaryContainer),
                )

                Column(
                    modifier = Modifier.fillMaxHeight().padding(start = 16.dp),
                    verticalArrangement = Arrangement.Top,
                ) {
                    Text(
                        text = item.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(top = 12.dp, bottom = 4.dp),
                    )

                    listOf(
                        Icons.Filled.LocationOn to item.city,
                        Icons.Filled.Square to item.area.toString(),
                        Icons.Filled.EuroSymbol to item.price.toString(),
                        Icons.Filled.MeetingRoom to item.rooms?.toString(),
                        Icons.Filled.Bed to item.bedrooms?.toString(),
                    ).forEach { (vectorImage, label) ->
                        label?.let {
                            IconLabel(
                                modifier = Modifier.padding(top = 2.dp),
                                icon = vectorImage,
                                iconSize = 16.dp,
                                label = it,
                                labelStyle = MaterialTheme.typography.labelLarge,
                            )
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
