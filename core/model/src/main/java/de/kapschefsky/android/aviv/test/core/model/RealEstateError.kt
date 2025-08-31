package de.kapschefsky.android.aviv.test.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class RealEstateError : Parcelable {
    @Parcelize
    data object General: RealEstateError(), Parcelable

    @Parcelize
    data class ApiError(val responseCode: Int): RealEstateError(), Parcelable
}