package de.kapschefsky.android.aviv.test.core.domain.mapper

import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiListItem
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel
import de.kapschefsky.android.aviv.test.core.model.RealEstate
import de.kapschefsky.android.aviv.test.core.model.RealEstateListItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealEstateApiMapper @Inject constructor() {
    fun mapRealEstateApiListItemsToDomain(apiListItems: List<RealEstateApiListItem>): List<RealEstateListItem> =
        apiListItems.map { apiListItem ->
            RealEstateListItem(
                id = apiListItem.id,
                bedrooms = apiListItem.bedrooms,
                city = apiListItem.city,
                area = apiListItem.area,
                url = apiListItem.url,
                price = apiListItem.price,
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
            url = estateApiModel.url,
            price = estateApiModel.price,
            professional = estateApiModel.professional,
            propertyType = estateApiModel.propertyType,
            offerType = estateApiModel.offerType,
            rooms = estateApiModel.rooms,
        )
}
