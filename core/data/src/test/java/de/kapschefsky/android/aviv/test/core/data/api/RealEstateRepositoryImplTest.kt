package de.kapschefsky.android.aviv.test.core.data.api

import arrow.core.right
import de.kapschefsky.android.aviv.test.core.data.api.mapper.RealEstateApiResponseMapper
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingsResponse
import de.kapschefsky.android.aviv.test.core.data.api.model.testRealEstateApiModel
import de.kapschefsky.android.aviv.test.core.data.api.model.testRealEstateListingsResponse
import de.kapschefsky.android.aviv.test.core.data.repository.RealEstateRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

class RealEstateRepositoryImplTest {
    private lateinit var mockRealEstateApi: RealEstateApi
    private lateinit var mockkRealEstateApiResponseMapper: RealEstateApiResponseMapper
    private lateinit var repository: RealEstateRepository

    @Before
    fun before() {
        mockRealEstateApi = mockk()
        mockkRealEstateApiResponseMapper = mockk(relaxed = true)

        repository = RealEstateRepositoryImpl(
            realEstateApi = mockRealEstateApi,
            realEstateApiResponseMapper = mockkRealEstateApiResponseMapper
        )
    }

    @Test
    fun `RealEstateListings are obtained from Api and processed via response mapper in any case`() =
        runTest {
            val testRealEstateListingsResponse = testRealEstateListingsResponse()
            val mockResponse: Response<RealEstateListingsResponse> =
                mockk(relaxed = true) { every { body() } returns testRealEstateListingsResponse }
            val mockCall: Call<RealEstateListingsResponse> =
                mockk { every { execute() } returns mockResponse }

            coEvery { mockRealEstateApi.getRealEstateListings() } returns mockCall
            every { mockkRealEstateApiResponseMapper.mapRealEstateListingsResponse(mockResponse) } returns testRealEstateListingsResponse.items.right()

            val result = repository.getRealEstateListings()

            coVerify { mockRealEstateApi.getRealEstateListings() }
            verify { mockkRealEstateApiResponseMapper.mapRealEstateListingsResponse(mockResponse) }

            assertTrue(result.isRight())
            assertEquals(testRealEstateListingsResponse.items, result.getOrNull())
        }

    @Test
    fun `RealEstate by id is obtained from Api and processed via response mapper in any case`() =
        runTest {
            val testRealEstateApiModel = testRealEstateApiModel()
            val mockResponse: Response<RealEstateApiModel> =
                mockk(relaxed = true) { every { body() } returns testRealEstateApiModel }
            val mockCall: Call<RealEstateApiModel> =
                mockk { every { execute() } returns mockResponse }

            coEvery { mockRealEstateApi.getRealEstateListing(testRealEstateApiModel.id) } returns mockCall
            every { mockkRealEstateApiResponseMapper.mapRealEstateApiModelResponse(mockResponse) } returns testRealEstateApiModel.right()

            val result = repository.getRealEstate(testRealEstateApiModel.id)

            coVerify { mockRealEstateApi.getRealEstateListing(testRealEstateApiModel.id) }
            verify { mockkRealEstateApiResponseMapper.mapRealEstateApiModelResponse(mockResponse) }

            assertTrue(result.isRight())
            assertEquals(testRealEstateApiModel, result.getOrNull())
        }
}
