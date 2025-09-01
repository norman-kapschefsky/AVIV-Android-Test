package de.kapschefsky.android.aviv.test.app.ui.components.realestate.details

import de.kapschefsky.android.aviv.test.app.ui.mapper.AreaUiMapper
import de.kapschefsky.android.aviv.test.app.ui.mapper.PriceUiMapper
import de.kapschefsky.android.aviv.test.core.model.RealEstate
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealEstateDetailsUiModelMapper
    @Inject
    constructor(
        private val priceUiMapper: PriceUiMapper,
        private val areaUiMapper: AreaUiMapper,
    ) {
        fun mapRealEstateToDetailsUiModel(realEstate: RealEstate): RealEstateDetailsUiModel =
            RealEstateDetailsUiModel(
                id = realEstate.id,
                imageUrl = realEstate.imageUrl,
                city = realEstate.city,
                area =
                    areaUiMapper.mapToDisplayableArea(
                        areaValue = realEstate.area,
                        areaMeasureUnit = realEstate.areaMeasureUnit,
                    ),
                price =
                    priceUiMapper.mapToDisplayablePrice(
                        priceValue = realEstate.price,
                        priceCurrencyCode = realEstate.priceCurrencyCode,
                    ),
                rooms = realEstate.rooms,
                bedrooms = realEstate.bedrooms,
            )
    }
