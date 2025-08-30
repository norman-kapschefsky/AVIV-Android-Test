package de.kapschefsky.android.aviv.test.app.ui.components.realestate.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import de.kapschefsky.android.aviv.test.app.ui.components.common.RemoteImage
import de.kapschefsky.android.aviv.test.app.ui.components.realestate.details.RealEstateDetailsUiState.Error
import de.kapschefsky.android.aviv.test.app.ui.components.realestate.details.RealEstateDetailsUiState.Loading
import de.kapschefsky.android.aviv.test.app.ui.components.realestate.details.RealEstateDetailsUiState.RealEstate
import de.kapschefsky.android.aviv.test.core.model.RealEstateId
import de.kapschefsky.android.aviv.test.core.ui.components.LoadingIndicator

@Composable
fun RealEstateDetailsUi(
    id: RealEstateId,
    modifier: Modifier = Modifier,
    viewModel: RealEstateDetailsViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRealEstateDetails(id)
    }

    when (val state = uiState.value) {
        Loading -> LoadingUi(modifier = modifier)
        is RealEstate ->
            RealEstateUi(
                realEstate = state.realEstate,
                modifier = modifier,
            )

        is Error.Loading -> LoadingErrorUi(modifier = modifier)
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
private fun RealEstateUi(
    realEstate: RealEstateDetailsUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
    ) {
        RemoteImage(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(32.dp))
                    .background(MaterialTheme.colorScheme.surfaceDim),
            imageUrl = realEstate.url,
        )

        Text(text = realEstate.toString())
    }
}

@Composable
private fun LoadingErrorUi(modifier: Modifier = Modifier) {
    Text("Error!")
}
