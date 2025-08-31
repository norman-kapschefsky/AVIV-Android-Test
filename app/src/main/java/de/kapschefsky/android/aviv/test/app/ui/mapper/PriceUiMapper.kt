package de.kapschefsky.android.aviv.test.app.ui.mapper

import java.text.NumberFormat
import java.util.Currency
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PriceUiMapper
    @Inject
    constructor() {
        fun mapToDisplayablePrice(
            priceValue: Int?,
            priceCurrencyCode: String?,
        ): String? =
            if (priceValue == null || priceCurrencyCode == null) {
                null
            } else {
                NumberFormat
                    .getCurrencyInstance()
                    .apply {
                        currency = Currency.getInstance(priceCurrencyCode)
                    }.format(priceValue)
            }
    }
