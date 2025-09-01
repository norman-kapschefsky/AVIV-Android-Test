package de.kapschefsky.android.aviv.test.core.domain.mapper

import de.kapschefsky.android.aviv.test.core.data.api.model.testRealEstateApiModel
import de.kapschefsky.android.aviv.test.core.data.api.model.testRealEstateListingsApiItemList
import de.kapschefsky.android.aviv.test.core.model.RealEstate
import de.kapschefsky.android.aviv.test.core.model.RealEstateListingsItem
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RealEstateApiMapperTest {
    private lateinit var mapper: RealEstateApiMapper

    @Before
    fun before() {
        mapper = RealEstateApiMapper()
    }

    @Test
    fun `Map list of RealEstateListingsApiItem to domain`() {
        val realEstateApiModelList = testRealEstateListingsApiItemList(10)
        val expectedRealEstateListingsItems = realEstateApiModelList.map { item ->
            RealEstateListingsItem(
                id = item.id,
                bedrooms = item.bedrooms,
                city = item.city,
                area = item.area,
                areaMeasureUnit = MEASURE_UNIT_SQUARE_UNIT,
                imageUrl = item.url,
                price = item.price,
                priceCurrencyCode = CURRENCY_EURO,
                professional = item.professional,
                propertyType = item.propertyType,
                offerType = item.offerType,
                rooms = item.rooms,
            )
        }

        val mappedRealEstateListingsItems = mapper.mapRealEstateListingsApiItemToDomain(realEstateApiModelList)

        assertEquals(expectedRealEstateListingsItems, mappedRealEstateListingsItems)
    }

    @Test
    fun `Map RealEstateApiModel to domain`() {
        repeat(5) {
            val realEstateApiModel = testRealEstateApiModel()
            val expectedRealEstate = RealEstate(
                id = realEstateApiModel.id,
                bedrooms = realEstateApiModel.bedrooms,
                city = realEstateApiModel.city,
                area = realEstateApiModel.area,
                areaMeasureUnit = MEASURE_UNIT_SQUARE_UNIT,
                imageUrl = realEstateApiModel.url,
                price = realEstateApiModel.price,
                priceCurrencyCode = CURRENCY_EURO,
                professional = realEstateApiModel.professional,
                propertyType = realEstateApiModel.propertyType,
                offerType = realEstateApiModel.offerType,
                rooms = realEstateApiModel.rooms,
            )


            val mappedRealEstate = mapper.mapRealEstateApiModelToDomain(realEstateApiModel)

            assertEquals(expectedRealEstate, mappedRealEstate)
        }
    }

    companion object {
        private const val CURRENCY_EURO = "EUR"
        private const val MEASURE_UNIT_SQUARE_UNIT = "m²"
    }
}