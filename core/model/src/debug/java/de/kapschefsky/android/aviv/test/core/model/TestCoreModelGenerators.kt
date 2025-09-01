package de.kapschefsky.android.aviv.test.core.model

import java.util.UUID
import kotlin.math.absoluteValue
import kotlin.random.Random

val testRealEstate: () -> RealEstate = {
    RealEstate(
        id = Random.nextInt().absoluteValue,
        bedrooms = Random.nextInt().absoluteValue,
        city = UUID.randomUUID().toString(),
        area = Random.nextInt().absoluteValue,
        imageUrl = "https://foo${Random.nextLong()}.com",
        price = Random.nextInt().absoluteValue,
        professional = UUID.randomUUID().toString(),
        propertyType = UUID.randomUUID().toString(),
        offerType = Random.nextInt(),
        areaMeasureUnit = UUID.randomUUID().toString(),
        priceCurrencyCode = "EUR",
        rooms = Random.nextInt().absoluteValue
    )
}

val testRealEstateListingsItem: () -> RealEstateListingsItem = {
    RealEstateListingsItem(
        id = Random.nextInt().absoluteValue,
        bedrooms = Random.nextInt().absoluteValue,
        city = UUID.randomUUID().toString(),
        area = Random.nextInt().absoluteValue,
        imageUrl = "https://foo${Random.nextLong()}.com",
        price = Random.nextInt().absoluteValue,
        professional = UUID.randomUUID().toString(),
        propertyType = UUID.randomUUID().toString(),
        offerType = Random.nextInt(),
        areaMeasureUnit = UUID.randomUUID().toString(),
        priceCurrencyCode = "EUR",
        rooms = Random.nextInt().absoluteValue
    )
}

fun testRealEstateListingsItems(count: Int): List<RealEstateListingsItem> =
    mutableListOf<RealEstateListingsItem>().apply {
        repeat(count) { realEstateId ->
            add(testRealEstateListingsItem().copy(id = realEstateId))
        }
    }