package de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings

import de.kapschefsky.android.aviv.test.app.ui.mapper.AreaUiMapper
import de.kapschefsky.android.aviv.test.app.ui.mapper.PriceUiMapper
import de.kapschefsky.android.aviv.test.core.model.testRealEstateListingsItems
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class RealEstateListingsItemUiModelMapperTest {
    private lateinit var mockPriceUiMapper: PriceUiMapper
    private lateinit var mockAreaUiMapper: AreaUiMapper
    private lateinit var mapper: RealEstateListingsItemUiModelMapper

    @Before
    fun before() {
        mockPriceUiMapper = mockk(relaxed = true)
        mockAreaUiMapper = mockk(relaxed = true)

        mapper =
            RealEstateListingsItemUiModelMapper(
                priceUiMapper = mockPriceUiMapper,
                areaUiMapper = mockAreaUiMapper,
            )
    }

    @Test
    fun `Map RealEstate model to RealEstateDetailsUiModel`() {
        val realEstateListingsItems = testRealEstateListingsItems(10)
        val uiModels = mapper.mapRealEstateListingsItemsToUiModels(realEstateListingsItems)

        assertEquals(realEstateListingsItems.size, uiModels.size)

        uiModels.forEachIndexed { index, uiModel ->
            val realEstate = realEstateListingsItems[index]

            assertEquals(realEstate.id, uiModel.id)
            assertEquals(realEstate.propertyType, uiModel.name)
            assertEquals(realEstate.imageUrl, uiModel.imageUrl)
            assertEquals(realEstate.city, uiModel.city)
            assertEquals(mockAreaUiMapper.mapToDisplayableArea(realEstate.area, realEstate.areaMeasureUnit), uiModel.area)
            assertEquals(mockPriceUiMapper.mapToDisplayablePrice(realEstate.price, realEstate.priceCurrencyCode), uiModel.price)
            assertEquals(realEstate.rooms, uiModel.rooms)
            assertEquals(realEstate.bedrooms, uiModel.bedrooms)
        }
    }
}
