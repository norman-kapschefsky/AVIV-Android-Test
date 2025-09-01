package de.kapschefsky.android.aviv.test.core.domain.usecases

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import de.kapschefsky.android.aviv.test.core.data.api.model.ApiError
import de.kapschefsky.android.aviv.test.core.data.repository.RealEstateRepository
import de.kapschefsky.android.aviv.test.core.domain.mapper.RealEstateApiMapper
import de.kapschefsky.android.aviv.test.core.model.RealEstate
import de.kapschefsky.android.aviv.test.core.model.RealEstateError
import de.kapschefsky.android.aviv.test.core.model.RealEstateId
import de.kapschefsky.android.aviv.test.core.model.RealEstateListingsItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealEstateUseCases @Inject constructor(
    private val realEstateRepository: RealEstateRepository,
    private val realEstateApiMapper: RealEstateApiMapper,
) {
    suspend fun getRealEstateListings(): Either<RealEstateError, List<RealEstateListingsItem>> =
        realEstateRepository.getRealEstateListings().fold(
            ifLeft = { apiError ->
                when (apiError) {
                    is ApiError.General -> RealEstateError.ApiError(responseCode = apiError.responseCode)
                    ApiError.EmptyResponse -> RealEstateError.General
                }.left()
            },
            ifRight = { realEstateListingsApiItems ->
                realEstateApiMapper
                    .mapRealEstateListingsApiItemToDomain(realEstateListingsApiItems)
                    .right()
            }
        )

    suspend fun getRealEstate(id: RealEstateId): Either<RealEstateError, RealEstate> =
        realEstateRepository.getRealEstate(id = id).fold(
            ifLeft = { apiError ->
                when (apiError) {
                    is ApiError.General -> RealEstateError.ApiError(responseCode = apiError.responseCode)
                    ApiError.EmptyResponse -> RealEstateError.General
                }.left()
            },
            ifRight = { estateApiModel ->
                realEstateApiMapper.mapRealEstateApiModelToDomain(estateApiModel).right()
            }
        )
}