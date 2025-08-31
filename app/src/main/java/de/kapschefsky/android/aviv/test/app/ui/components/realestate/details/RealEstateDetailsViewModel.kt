package de.kapschefsky.android.aviv.test.app.ui.components.realestate.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import de.kapschefsky.android.aviv.test.app.ui.components.realestate.details.RealEstateDetailsUiState.Loading
import de.kapschefsky.android.aviv.test.core.domain.common.CoroutineDispatcherProvider
import de.kapschefsky.android.aviv.test.core.domain.usecases.RealEstateUseCases
import de.kapschefsky.android.aviv.test.core.model.EMPTY
import de.kapschefsky.android.aviv.test.core.model.RealEstateId
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RealEstateDetailsViewModel
    @Inject
    constructor(
        private val realEstateUseCases: RealEstateUseCases,
        private val coroutineDispatcherProvider: CoroutineDispatcherProvider,
    ) : ViewModel() {
        private val _uiState: MutableStateFlow<RealEstateDetailsUiState> = MutableStateFlow(Loading)
        val uiState: StateFlow<RealEstateDetailsUiState> = _uiState

        fun loadRealEstateDetails(
            id: RealEstateId,
            coroutineScope: CoroutineScope = viewModelScope,
        ) {
            _uiState.value = Loading

            coroutineScope.launch(coroutineDispatcherProvider.io) {
                _uiState.value =
                    realEstateUseCases.getRealEstate(id).fold(
                        ifLeft = RealEstateDetailsUiState.Error::Loading,
                        ifRight = { realEstate ->
                            RealEstateDetailsUiState.RealEstate(
                                RealEstateDetailsUiModel(
                                    id = realEstate.id,
                                    url = realEstate.url,
                                    city = realEstate.city,
                                    area =
                                        listOfNotNull(
                                            realEstate.area.toString(),
                                            realEstate.areaUnitSymbol,
                                        ).joinToString(EMPTY),
                                    price =
                                        listOfNotNull(
                                            realEstate.price.toString(),
                                            realEstate.priceCurrencySymbol,
                                        ).joinToString(EMPTY),
                                    rooms = realEstate.rooms,
                                    bedrooms = realEstate.bedrooms,
                                ),
                            )
                        },
                    )
            }
        }
    }
