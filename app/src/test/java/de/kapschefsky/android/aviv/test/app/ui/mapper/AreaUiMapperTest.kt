package de.kapschefsky.android.aviv.test.app.ui.mapper

import de.kapschefsky.android.aviv.test.core.model.ONE
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test

class AreaUiMapperTest {
    private lateinit var areaMapper: AreaUiMapper

    @Before
    fun before() {
        areaMapper = AreaUiMapper()
    }

    @Test
    fun `Displayable area is null if one of value or measure unit is null`() {
        listOf(
            null to null,
            null to MEASURE_UNIT_SQUARE_METER,
            ONE to null,
        ).forEach { (areaValue, areaMeasureUnit) ->
            assertNull(
                areaMapper.mapToDisplayableArea(
                    areaValue = areaValue,
                    areaMeasureUnit = areaMeasureUnit,
                ),
            )
        }
    }

    @Test
    fun `Displayable area is returned if value and measure unit are not null`() {
        val displayableArea =
            areaMapper.mapToDisplayableArea(
                areaValue = ONE,
                areaMeasureUnit = MEASURE_UNIT_SQUARE_METER,
            )

        val expectedDisplayableArea = "$ONE $MEASURE_UNIT_SQUARE_METER"

        assertEquals(expectedDisplayableArea, displayableArea)
    }

    companion object {
        private const val MEASURE_UNIT_SQUARE_METER = "m²"
    }
}
