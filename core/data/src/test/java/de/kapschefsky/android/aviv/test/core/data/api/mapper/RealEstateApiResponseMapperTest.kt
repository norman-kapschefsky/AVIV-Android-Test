package de.kapschefsky.android.aviv.test.core.data.api.mapper

import de.kapschefsky.android.aviv.test.core.data.api.model.ApiError
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingsResponse
import de.kapschefsky.android.aviv.test.core.data.api.model.testRealEstateApiModel
import de.kapschefsky.android.aviv.test.core.data.api.model.testRealEstateListingsResponse
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class RealEstateApiResponseMapperTest {
    private lateinit var mapper: RealEstateApiResponseMapper

    @Before
    fun before() {
        mapper = RealEstateApiResponseMapper()
    }

    @Test
    fun `Map RealEstateListingsResponse returns ApiError-EmptyResponse if response body is null`() {
        val mockResponse: Response<RealEstateListingsResponse> = mockk {
            every { isSuccessful } returns true
            every { body() } returns null
        }

        val mappedResponse = mapper.mapRealEstateListingsResponse(mockResponse)

        assertTrue(mappedResponse.isLeft())
        assertEquals(ApiError.EmptyResponse, mappedResponse.leftOrNull())
    }

    @Test
    fun `Map RealEstateListingsResponse returns ApiError-General if response is not successful`() {
        val responseCode = 42
        val mockResponse: Response<RealEstateListingsResponse> = mockk {
            every { isSuccessful } returns false
            every { code() } returns responseCode
        }

        val mappedResponse = mapper.mapRealEstateListingsResponse(mockResponse)

        assertTrue(mappedResponse.isLeft())
        assertEquals(ApiError.General(responseCode = responseCode), mappedResponse.leftOrNull())
    }

    @Test
    fun `Map RealEstateListingsResponse returns RealEstateListingsItems if response is successful`() {
        val testRealEstateListingsResponse = testRealEstateListingsResponse()
        val mockResponse: Response<RealEstateListingsResponse> = mockk {
            every { isSuccessful } returns true
            every { body() } returns testRealEstateListingsResponse
        }

        val mappedResponse = mapper.mapRealEstateListingsResponse(mockResponse)

        assertTrue(mappedResponse.isRight())
        assertEquals(testRealEstateListingsResponse.items, mappedResponse.getOrNull())
    }

    @Test
    fun `Map RealEstateApiModelResponse returns ApiError-EmptyResponse if response body is null`() {
        val mockResponse: Response<RealEstateApiModel> = mockk {
            every { isSuccessful } returns true
            every { body() } returns null
        }

        val mappedResponse = mapper.mapRealEstateApiModelResponse(mockResponse)

        assertTrue(mappedResponse.isLeft())
        assertEquals(ApiError.EmptyResponse, mappedResponse.leftOrNull())
    }

    @Test
    fun `Map RealEstateApiModelResponse returns ApiError-General if response is not successful`() {
        val responseCode = 42
        val mockResponse: Response<RealEstateApiModel> = mockk {
            every { isSuccessful } returns false
            every { code() } returns responseCode
        }

        val mappedResponse = mapper.mapRealEstateApiModelResponse(mockResponse)

        assertTrue(mappedResponse.isLeft())
        assertEquals(ApiError.General(responseCode = responseCode), mappedResponse.leftOrNull())
    }

    @Test
    fun `Map RealEstateApiModelResponse returns RealEstateListingsItems if response is successful`() {
        val testRealEstateApiModel = testRealEstateApiModel()
        val mockResponse: Response<RealEstateApiModel> = mockk {
            every { isSuccessful } returns true
            every { body() } returns testRealEstateApiModel
        }

        val mappedResponse = mapper.mapRealEstateApiModelResponse(mockResponse)

        assertTrue(mappedResponse.isRight())
        assertEquals(testRealEstateApiModel, mappedResponse.getOrNull())
    }
}
