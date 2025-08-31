package de.kapschefsky.android.aviv.test.core.domain.mapper

import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingsApiItem
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel
import de.kapschefsky.android.aviv.test.core.model.RealEstate
import de.kapschefsky.android.aviv.test.core.model.RealEstateListingsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealEstateApiMapper @Inject constructor() {
    fun mapRealEstateListingsApiItemToDomain(apiListItems: List<RealEstateListingsApiItem>): List<RealEstateListingsItem> =
        apiListItems.map { apiListItem ->
            RealEstateListingsItem(
                id = apiListItem.id,
                bedrooms = apiListItem.bedrooms,
                city = apiListItem.city,
                area = apiListItem.area,
                areaMeasureUnit = MEASURE_UNIT_SQUARE_UNIT,
                url = apiListItem.url,
                price = apiListItem.price,
                priceCurrencyCode = CURRENCY_EURO,
                professional = apiListItem.professional,
                propertyType = apiListItem.propertyType,
                offerType = apiListItem.offerType,
                rooms = apiListItem.rooms,
            )
        }

    fun mapRealEstateApiModelToDomain(estateApiModel: RealEstateApiModel): RealEstate =
        RealEstate(
            id = estateApiModel.id,
            bedrooms = estateApiModel.bedrooms,
            city = estateApiModel.city,
            area = estateApiModel.area,
            areaMeasureUnit = MEASURE_UNIT_SQUARE_UNIT,
            url = estateApiModel.url,
            price = estateApiModel.price,
            priceCurrencyCode = CURRENCY_EURO,
            professional = estateApiModel.professional,
            propertyType = estateApiModel.propertyType,
            offerType = estateApiModel.offerType,
            rooms = estateApiModel.rooms,
        )

    companion object {
        private const val CURRENCY_EURO = "EUR"
        private const val MEASURE_UNIT_SQUARE_UNIT = "m²"
    }
}
