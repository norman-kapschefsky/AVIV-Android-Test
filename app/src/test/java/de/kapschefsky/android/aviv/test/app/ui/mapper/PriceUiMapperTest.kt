package de.kapschefsky.android.aviv.test.app.ui.mapper

import de.kapschefsky.android.aviv.test.core.model.ONE
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

class PriceUiMapperTest {
    private lateinit var priceMapper: PriceUiMapper

    @Before
    fun before() {
        priceMapper = PriceUiMapper()
    }

    @Test
    fun `Displayable price is null if one of value or currency code is null`() {
        listOf(
            null to null,
            null to CURRENCY_CODE_EURO,
            ONE to null,
        ).forEach { (priceValue, currencyCode) ->
            assertNull(
                priceMapper.mapToDisplayablePrice(
                    priceValue = priceValue,
                    priceCurrencyCode = currencyCode,
                ),
            )
        }
    }

    @Test
    fun `Displayable price is returned if value and currency code are not null`() {
        val defaultLocale = Locale.getDefault()
        val enLocale = Locale.ENGLISH

        Locale.setDefault(enLocale)

        val displayablePrice =
            priceMapper.mapToDisplayablePrice(
                priceValue = ONE,
                priceCurrencyCode = CURRENCY_CODE_EURO,
            )

        val expectedDisplayablePrice =
            NumberFormat
                .getCurrencyInstance(enLocale)
                .apply {
                    currency = Currency.getInstance(CURRENCY_CODE_EURO)
                }.format(ONE)

        assertEquals(expectedDisplayablePrice, displayablePrice)

        Locale.setDefault(defaultLocale)
    }

    companion object {
        private const val CURRENCY_CODE_EURO = "EUR"
    }
}
