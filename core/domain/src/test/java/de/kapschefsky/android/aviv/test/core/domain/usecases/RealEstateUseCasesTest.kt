package de.kapschefsky.android.aviv.test.core.domain.usecases

import arrow.core.left
import arrow.core.right
import de.kapschefsky.android.aviv.test.core.data.api.model.ApiError
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingsApiItem
import de.kapschefsky.android.aviv.test.core.data.repository.RealEstateRepository
import de.kapschefsky.android.aviv.test.core.domain.mapper.RealEstateApiMapper
import de.kapschefsky.android.aviv.test.core.model.RealEstate
import de.kapschefsky.android.aviv.test.core.model.RealEstateError
import de.kapschefsky.android.aviv.test.core.model.RealEstateListingsItem
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RealEstateUseCasesTest {
    private lateinit var mockRealEstateApiMapper: RealEstateApiMapper
    private lateinit var mockRealEstateRepository: RealEstateRepository
    private lateinit var realEstateUseCases: RealEstateUseCases

    @Before
    fun before() {
        mockRealEstateApiMapper = mockk()
        mockRealEstateRepository = mockk()

        realEstateUseCases = RealEstateUseCases(
            realEstateRepository = mockRealEstateRepository,
            realEstateApiMapper = mockRealEstateApiMapper
        )
    }

    @Test
    fun `Getting RealEstateListings returns RealEstateError if obtaining from repository fails`() =
        runTest {
            listOf(
                ApiError.EmptyResponse to RealEstateError.General,
                ApiError.General(0) to RealEstateError.ApiError(0)
            ).forEach { (repositoryError, expectedUseCaseError) ->
                coEvery { mockRealEstateRepository.getRealEstateListings() } returns repositoryError.left()

                val result = realEstateUseCases.getRealEstateListings()

                coVerify { mockRealEstateRepository.getRealEstateListings() }

                assertTrue(result.isLeft())
                assertEquals(expectedUseCaseError, result.leftOrNull())
            }
        }

    @Test
    fun `Getting RealEstateListings returns list of RealEstateListingsItems if obtaining from repository is successful`() =
        runTest {
            val mockRealEstateListingsApiItems: List<RealEstateListingsApiItem> = mockk()
            val mockRealEstateListingsItems: List<RealEstateListingsItem> = mockk()

            coEvery { mockRealEstateRepository.getRealEstateListings() } returns mockRealEstateListingsApiItems.right()
            every { mockRealEstateApiMapper.mapRealEstateListingsApiItemToDomain(mockRealEstateListingsApiItems) } returns mockRealEstateListingsItems

            val result = realEstateUseCases.getRealEstateListings()

            coVerify { mockRealEstateRepository.getRealEstateListings() }
            coVerify { mockRealEstateApiMapper.mapRealEstateListingsApiItemToDomain(mockRealEstateListingsApiItems) }

            assertTrue(result.isRight())
            assertEquals(mockRealEstateListingsItems, result.getOrNull())
        }

    @Test
    fun `Getting RealEstate by id returns RealEstateError if obtaining from repository fails`() =
        runTest {
            listOf(
                ApiError.EmptyResponse to RealEstateError.General,
                ApiError.General(0) to RealEstateError.ApiError(0)
            ).forEachIndexed { realEstateId, (repositoryError, expectedUseCaseError) ->

                coEvery { mockRealEstateRepository.getRealEstate(realEstateId) } returns repositoryError.left()

                val result = realEstateUseCases.getRealEstate(realEstateId)

                coVerify { mockRealEstateRepository.getRealEstate(realEstateId) }

                assertTrue(result.isLeft())
                assertEquals(expectedUseCaseError, result.leftOrNull())
            }
        }

    @Test
    fun `Getting RealEstate by id returns RealEstate if obtaining from repository is successful`() =
        runTest {
            val realEstateId = 2025
            val mockRealEstateApiModel: RealEstateApiModel = mockk()
            val mockRealEstate: RealEstate = mockk()

            coEvery { mockRealEstateRepository.getRealEstate(realEstateId) } returns mockRealEstateApiModel.right()
            every { mockRealEstateApiMapper.mapRealEstateApiModelToDomain(mockRealEstateApiModel) } returns mockRealEstate

            val result = realEstateUseCases.getRealEstate(realEstateId)

            coVerify { mockRealEstateRepository.getRealEstate(realEstateId) }
            coVerify { mockRealEstateApiMapper.mapRealEstateApiModelToDomain(mockRealEstateApiModel) }

            assertTrue(result.isRight())
            assertEquals(mockRealEstate, result.getOrNull())
        }
}
