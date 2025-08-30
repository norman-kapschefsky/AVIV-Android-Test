package de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings

import de.kapschefsky.android.aviv.test.core.model.RealEstateId

data class RealEstateListItemUiModel(
    val id: RealEstateId,
    val name: String,
    val url: String?,
    val city: String,
    val area: Int,
    val price: Int,
    val rooms: Int?,
    val bedrooms: Int?,
)
