package de.kapschefsky.android.aviv.test.core.data.api

import arrow.core.Either
import arrow.core.left
import de.kapschefsky.android.aviv.test.core.data.api.mapper.RealEstateApiResponseMapper
import de.kapschefsky.android.aviv.test.core.data.api.model.ApiError
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiListItem
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel
import de.kapschefsky.android.aviv.test.core.data.repository.RealEstateRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RealEstateRepositoryImpl @Inject constructor(
    private val realEstateApi: RealEstateApi,
    private val realEstateApiResponseMapper: RealEstateApiResponseMapper,
): RealEstateRepository {
    override suspend fun getRealEstateListItems(): Either<ApiError, List<RealEstateApiListItem>> =
       realEstateApi.getRealEstateListings()
           .execute()
           .let(realEstateApiResponseMapper::mapListingResponse)

    override suspend fun getRealEstate(id: Int): Either<ApiError, RealEstateApiModel> =
        ApiError.General.left()
}