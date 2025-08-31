package de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings

import de.kapschefsky.android.aviv.test.core.model.RealEstateId

data class RealEstateListingsItemUiModel(
    val id: RealEstateId,
    val name: String,
    val url: String?,
    val city: String,
    val areaLabel: String?,
    val priceLabel: String?,
    val rooms: Int?,
    val bedrooms: Int?,
)
