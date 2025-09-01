package de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings

import de.kapschefsky.android.aviv.test.app.ui.mapper.AreaUiMapper
import de.kapschefsky.android.aviv.test.app.ui.mapper.PriceUiMapper
import de.kapschefsky.android.aviv.test.core.model.RealEstateListingsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealEstateListingsItemUiModelMapper
    @Inject
    constructor(
        private val priceUiMapper: PriceUiMapper,
        private val areaUiMapper: AreaUiMapper,
    ) {
        fun mapRealEstateListingsItemsToUiModels(estateListItems: List<RealEstateListingsItem>): List<RealEstateListingsItemUiModel> =
            estateListItems.map { estateListItem ->
                RealEstateListingsItemUiModel(
                    id = estateListItem.id,
                    name = estateListItem.propertyType,
                    imageUrl = estateListItem.imageUrl,
                    city = estateListItem.city,
                    area =
                        areaUiMapper.mapToDisplayableArea(
                            areaValue = estateListItem.area,
                            areaMeasureUnit = estateListItem.areaMeasureUnit,
                        ),
                    price =
                        priceUiMapper.mapToDisplayablePrice(
                            priceValue = estateListItem.price,
                            priceCurrencyCode = estateListItem.priceCurrencyCode,
                        ),
                    rooms = estateListItem.rooms,
                    bedrooms = estateListItem.bedrooms,
                )
            }
    }
