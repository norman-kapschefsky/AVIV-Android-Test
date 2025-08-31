package de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings

import de.kapschefsky.android.aviv.test.core.model.RealEstateError

sealed class RealEstateListingsUiState {
    data object Loading : RealEstateListingsUiState()

    data class RealEstateListings(
        val items: List<RealEstateListingsItemUiModel>,
    ) : RealEstateListingsUiState()

    sealed class Error : RealEstateListingsUiState() {
        data class Loading(
            val error: RealEstateError,
        ) : Error()
    }
}
