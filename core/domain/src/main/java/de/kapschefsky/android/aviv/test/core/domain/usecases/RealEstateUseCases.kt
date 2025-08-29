package de.kapschefsky.android.aviv.test.core.domain.usecases

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import de.kapschefsky.android.aviv.test.core.data.repository.RealEstateRepository
import de.kapschefsky.android.aviv.test.core.domain.mapper.RealEstateApiMapper
import de.kapschefsky.android.aviv.test.core.model.RealEstate
import de.kapschefsky.android.aviv.test.core.model.RealEstateError
import de.kapschefsky.android.aviv.test.core.model.RealEstateListItem
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RealEstateUseCases @Inject constructor(
    private val realEstateRepository: RealEstateRepository,
    private val realEstateApiMapper: RealEstateApiMapper,
) {
    suspend fun getRealEstateListItems(): Either<RealEstateError, List<RealEstateListItem>> =
        realEstateRepository.getRealEstateListItems().fold(
            ifLeft = {
                // TODO Error handling
                RealEstateError.General.left()
            },
            ifRight = { realEstateApiMapper.mapRealEstateApiListItemsToDomain(it).right() }
        )

    suspend fun getRealEstate(id: Int): Either<RealEstateError, RealEstate> =
        realEstateRepository.getRealEstate(id = id).fold(
            ifLeft = {
                // TODO Error handling
                RealEstateError.General.left()
            },
            ifRight = { realEstateApiMapper.mapRealEstateApiModelToDomain(it).right() }
        )
}