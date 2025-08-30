package de.kapschefsky.android.aviv.test.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RealEstateListItem(
    val id: RealEstateId,
    val bedrooms: Int?,
    val city: String,
    val area: Int,
    val url: String?,
    val price: Int,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms: Int?
): Parcelable
