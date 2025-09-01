package de.kapschefsky.android.aviv.test.core.data.api

import arrow.core.Either
import de.kapschefsky.android.aviv.test.core.data.api.mapper.RealEstateApiResponseMapper
import de.kapschefsky.android.aviv.test.core.data.api.model.ApiError
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingsApiItem
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel
import de.kapschefsky.android.aviv.test.core.data.repository.RealEstateRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RealEstateRepositoryImpl @Inject constructor(
    private val realEstateApi: RealEstateApi,
    private val realEstateApiResponseMapper: RealEstateApiResponseMapper,
): RealEstateRepository {
    override suspend fun getRealEstateListings(): Either<ApiError, List<RealEstateListingsApiItem>> =
       realEstateApi.getRealEstateListings()
           .execute()
           .let(realEstateApiResponseMapper::mapRealEstateListingsResponse)

    override suspend fun getRealEstate(id: Int): Either<ApiError, RealEstateApiModel> =
        realEstateApi.getRealEstateListing(id)
            .execute()
            .let(realEstateApiResponseMapper::mapRealEstateApiModelResponse)
}