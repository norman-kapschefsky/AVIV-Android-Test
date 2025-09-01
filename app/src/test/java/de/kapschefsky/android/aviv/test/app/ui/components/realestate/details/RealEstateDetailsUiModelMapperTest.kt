package de.kapschefsky.android.aviv.test.app.ui.components.realestate.details

import de.kapschefsky.android.aviv.test.app.ui.mapper.AreaUiMapper
import de.kapschefsky.android.aviv.test.app.ui.mapper.PriceUiMapper
import de.kapschefsky.android.aviv.test.core.model.testRealEstate
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RealEstateDetailsUiModelMapperTest {
    private lateinit var mockPriceUiMapper: PriceUiMapper
    private lateinit var mockAreaUiMapper: AreaUiMapper
    private lateinit var mapper: RealEstateDetailsUiModelMapper

    @Before
    fun before() {
        mockPriceUiMapper = mockk(relaxed = true)
        mockAreaUiMapper = mockk(relaxed = true)

        mapper =
            RealEstateDetailsUiModelMapper(
                priceUiMapper = mockPriceUiMapper,
                areaUiMapper = mockAreaUiMapper,
            )
    }

    @Test
    fun `Map RealEstate model to RealEstateDetailsUiModel`() {
        val realEstate = testRealEstate()
        val mappedRealEstateDetailsUiModel = mapper.mapRealEstateToDetailsUiModel(realEstate)

        assertEquals(realEstate.id, mappedRealEstateDetailsUiModel.id)
        assertEquals(realEstate.imageUrl, mappedRealEstateDetailsUiModel.imageUrl)
        assertEquals(realEstate.city, mappedRealEstateDetailsUiModel.city)
        assertEquals(
            mockAreaUiMapper.mapToDisplayableArea(realEstate.area, realEstate.areaMeasureUnit),
            mappedRealEstateDetailsUiModel.area,
        )
        assertEquals(
            mockPriceUiMapper.mapToDisplayablePrice(realEstate.price, realEstate.priceCurrencyCode),
            mappedRealEstateDetailsUiModel.price,
        )
        assertEquals(realEstate.rooms, mappedRealEstateDetailsUiModel.rooms)
        assertEquals(realEstate.bedrooms, mappedRealEstateDetailsUiModel.bedrooms)
    }
}
