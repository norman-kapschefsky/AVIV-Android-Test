package de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import de.kapschefsky.android.aviv.test.core.model.RealEstateId
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
        CircularProgressIndicator()
    }
}

@Composable
private fun ListingsUi(
    items: List<RealEstateListItemUiModel>,
    onRealEstateItemClicked: (RealEstateId) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn {
        items(
            items = items,
            key = { item -> item.id },
        ) { item ->
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickableRipple { onRealEstateItemClicked(item.id) },
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    text = item.id.toString(),
                )
            }
        }
    }
}

@Composable
private fun LoadingErrorUi(modifier: Modifier = Modifier) {
    Text("Error!")
}
