package de.kapschefsky.android.aviv.test.app.ui.components.realestate.details

import de.kapschefsky.android.aviv.test.core.model.RealEstateError

sealed class RealEstateDetailsUiState {
    data object Loading : RealEstateDetailsUiState()

    data class RealEstate(
        val realEstate: RealEstateDetailsUiModel,
    ) : RealEstateDetailsUiState()

    sealed class Error : RealEstateDetailsUiState() {
        data class Loading(
            val error: RealEstateError,
        ) : Error()
    }
}
