package de.kapschefsky.android.aviv.test.app.ui.components.realestate.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import de.kapschefsky.android.aviv.test.app.ui.components.realestate.details.RealEstateDetailsUiState.Error
import de.kapschefsky.android.aviv.test.app.ui.components.realestate.details.RealEstateDetailsUiState.Loading
import de.kapschefsky.android.aviv.test.app.ui.components.realestate.details.RealEstateDetailsUiState.RealEstate
import de.kapschefsky.android.aviv.test.core.model.ONE
import de.kapschefsky.android.aviv.test.core.model.RealEstateId
import de.kapschefsky.android.aviv.test.core.ui.components.Headline
import de.kapschefsky.android.aviv.test.core.ui.components.LoadingIndicator

@Composable
fun RealEstateDetailsUi(
    id: RealEstateId,
    headlineText: String,
    modifier: Modifier = Modifier,
    viewModel: RealEstateDetailsViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRealEstateDetails(id)
    }

    Column(
        modifier =
            modifier
                .clip(RoundedCornerShape(32.dp))
                .background(MaterialTheme.colorScheme.surface),
    ) {
        Headline(
            modifier = Modifier.padding(16.dp),
            text = headlineText,
        )

        when (val state = uiState.value) {
            Loading -> LoadingIndicator(containerModifier = Modifier.fillMaxSize())
            is RealEstate ->
                RealEstateUi(
                    realEstate = state.realEstate,
                    modifier = modifier,
                )

            is Error.Loading ->
                ErrorInfoBox(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(R.string.real_estate_details_error_loading_general),
                    buttonLabel = stringResource(R.string.button_error_retry),
                    onButtonClicked = { viewModel.loadRealEstateDetails(id) },
                )
        }
    }
}

@Composable
private fun RealEstateUi(
    realEstate: RealEstateDetailsUiModel,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 16.dp),
    ) {
        item(key = "image") {
            RemoteImage(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer),
                imageUrl = realEstate.url,
            )
        }

        item(key = "spaceBelowImage") {
            Spacer(
                Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(MaterialTheme.colorScheme.surfaceContainer),
            )
        }

        listOf(
            Triple(
                Icons.Filled.LocationOn,
                R.string.real_estate_details_label_location,
                realEstate.city,
            ),
            Triple(
                Icons.Filled.EuroSymbol,
                R.string.real_estate_details_label_price,
                realEstate.price,
            ),
            Triple(
                Icons.Filled.Square,
                R.string.real_estate_details_label_living_area,
                realEstate.area,
            ),
            Triple(
                Icons.Filled.MeetingRoom,
                R.string.real_estate_details_label_rooms,
                realEstate.rooms,
            ),
            Triple(
                Icons.Filled.Bed,
                R.string.real_estate_details_label_bedrooms,
                realEstate.bedrooms,
            ),
        ).forEachIndexed { index, (imageVector, labelResId, value) ->
            item(key = "info_$index") {
                Row(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceContainer)
                            .padding(top = 12.dp, start = 8.dp, end = 8.dp),
                ) {
                    IconLabel(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        icon = imageVector,
                        iconTintColor = MaterialTheme.colorScheme.primary,
                        label = stringResource(labelResId),
                        labelColor = MaterialTheme.colorScheme.primary,
                    )

                    Spacer(Modifier.weight(ONE.toFloat()))

                    Text(
                        modifier = Modifier.padding(end = 16.dp),
                        text =
                            value?.toString()
                                ?: stringResource(R.string.real_estate_details_value_placeholder),
                    )
                }
            }
        }

        item(key = "bottomContentEnd") {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(32.dp)
                        .clip(RoundedCornerShape(bottomStart = 32.dp, bottomEnd = 32.dp))
                        .background(MaterialTheme.colorScheme.surfaceContainer),
            )
        }

        item(key = "spaceEnd") { Spacer(Modifier.height(64.dp)) }
    }
}
