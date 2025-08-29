package de.kapschefsky.android.aviv.test.core.data.repository

import arrow.core.Either
import de.kapschefsky.android.aviv.test.core.data.api.model.ApiError
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiListItem
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel

interface RealEstateRepository {
    suspend fun getRealEstateListItems(): Either<ApiError, List<RealEstateApiListItem>>

    suspend fun getRealEstate(id: Int): Either<ApiError, RealEstateApiModel>
}