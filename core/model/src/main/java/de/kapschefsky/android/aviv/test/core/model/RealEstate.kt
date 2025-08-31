package de.kapschefsky.android.aviv.test.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RealEstate(
    val id: RealEstateId,
    val bedrooms: Int?,
    val city: String,
    val area: Int,
    val areaMeasureUnit: String,
    val url: String?,
    val price: Int?,
    val priceCurrencyCode: String?,
    val professional: String,
    val propertyType: String,
    val offerType: Int,
    val rooms: Int?
): Parcelable
