package de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings.RealEstateListingsUiState.Loading
import de.kapschefsky.android.aviv.test.core.domain.common.CoroutineDispatcherProvider
import de.kapschefsky.android.aviv.test.core.domain.usecases.RealEstateUseCases
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealEstateListingsViewModel
    @Inject
    constructor(
        private val realEstateUseCases: RealEstateUseCases,
        private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    ) : ViewModel() {
        private val _uiState: MutableStateFlow<RealEstateListingsUiState> = MutableStateFlow(Loading)
        val uiState: StateFlow<RealEstateListingsUiState> = _uiState

        fun loadRealEstateListings(coroutineScope: CoroutineScope = viewModelScope) {
            _uiState.value = Loading

            coroutineScope.launch(coroutineDispatcherProvider.io) {
                _uiState.value =
                    realEstateUseCases.getRealEstateListItems().fold(
                        ifLeft = RealEstateListingsUiState.Error::Loading,
                        ifRight = { estateListItems ->
                            RealEstateListingsUiState.RealEstateListings(
                                items =
                                    estateListItems.map { estateListItem ->
                                        RealEstateListItemUiModel(
                                            id = estateListItem.id,
                                            url = estateListItem.url,
                                        )
                                    },
                            )
                        },
                    )
            }
        }
    }
