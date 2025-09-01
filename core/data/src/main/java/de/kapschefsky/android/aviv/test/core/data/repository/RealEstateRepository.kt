package de.kapschefsky.android.aviv.test.core.data.repository

import arrow.core.Either
import de.kapschefsky.android.aviv.test.core.data.api.model.ApiError
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingsApiItem
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel

interface RealEstateRepository {
    suspend fun getRealEstateListings(): Either<ApiError, List<RealEstateListingsApiItem>>

    suspend fun getRealEstate(id: Int): Either<ApiError, RealEstateApiModel>
}