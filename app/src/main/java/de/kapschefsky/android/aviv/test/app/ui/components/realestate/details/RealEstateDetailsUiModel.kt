package de.kapschefsky.android.aviv.test.app.ui.components.realestate.details

import de.kapschefsky.android.aviv.test.core.model.RealEstateId

data class RealEstateDetailsUiModel(
    val id: RealEstateId,
    val url: String?,
    val city: String,
    val area: String?,
    val price: String?,
    val rooms: Int?,
    val bedrooms: Int?,
)
