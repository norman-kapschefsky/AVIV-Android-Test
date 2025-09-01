package de.kapschefsky.android.aviv.test.core.domain.mapper

import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingsApiItem
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel
import de.kapschefsky.android.aviv.test.core.model.RealEstate
import de.kapschefsky.android.aviv.test.core.model.RealEstateListingsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealEstateApiMapper @Inject constructor() {
    fun mapRealEstateListingsApiItemToDomain(apiListingItems: List<RealEstateListingsApiItem>): List<RealEstateListingsItem> =
        apiListingItems.map { item ->
            RealEstateListingsItem(
                id = item.id,
                bedrooms = item.bedrooms,
                city = item.city,
                area = item.area,
                areaMeasureUnit = MEASURE_UNIT_SQUARE_UNIT,
                imageUrl = item.url,
                price = item.price,
                priceCurrencyCode = CURRENCY_EURO,
                professional = item.professional,
                propertyType = item.propertyType,
                offerType = item.offerType,
                rooms = item.rooms,
            )
        }

    fun mapRealEstateApiModelToDomain(estateApiModel: RealEstateApiModel): RealEstate =
        RealEstate(
            id = estateApiModel.id,
            bedrooms = estateApiModel.bedrooms,
            city = estateApiModel.city,
            area = estateApiModel.area,
            areaMeasureUnit = MEASURE_UNIT_SQUARE_UNIT,
            imageUrl = estateApiModel.url,
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
