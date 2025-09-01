@file:Suppress("unused")

package de.kapschefsky.android.aviv.test.core.data.api.model

import java.util.UUID
import kotlin.math.absoluteValue
import kotlin.random.Random

val testApiError: () -> ApiError = {
    listOf(
        ApiError.General(0),
        ApiError.EmptyResponse
    ).random()
}

val testRealEstateApiModel: () -> RealEstateApiModel = {
    RealEstateApiModel(
        id = Random.nextInt().absoluteValue,
        bedrooms = Random.nextInt().absoluteValue,
        city = UUID.randomUUID().toString(),
        area = Random.nextInt().absoluteValue,
        url = "https://foo${Random.nextLong().absoluteValue}.com",
        price = Random.nextInt().absoluteValue,
        professional = UUID.randomUUID().toString(),
        propertyType = UUID.randomUUID().toString(),
        offerType = Random.nextInt().absoluteValue,
        rooms = Random.nextInt().absoluteValue
    )
}

fun testRealEstateApiModelList(count: Int): List<RealEstateApiModel> =
    mutableListOf<RealEstateApiModel>().apply {
        repeat(count) { realEstateId ->
            add(testRealEstateApiModel().copy(id = realEstateId))
        }
    }

val testRealEstateListingsApiItem: () -> RealEstateListingsApiItem = {
    RealEstateListingsApiItem(
        id = Random.nextInt().absoluteValue,
        bedrooms = Random.nextInt().absoluteValue,
        city = UUID.randomUUID().toString(),
        area = Random.nextInt().absoluteValue,
        url = "https://foo${Random.nextLong().absoluteValue}.com",
        price = Random.nextInt().absoluteValue,
        professional = UUID.randomUUID().toString(),
        propertyType = UUID.randomUUID().toString(),
        offerType = Random.nextInt().absoluteValue,
        rooms = Random.nextInt().absoluteValue
    )
}

fun testRealEstateListingsApiItemList(count: Int): List<RealEstateListingsApiItem> =
    mutableListOf<RealEstateListingsApiItem>().apply {
        repeat(count) { realEstateId ->
            add(testRealEstateListingsApiItem().copy(id = realEstateId))
        }
    }

internal val testRealEstateListingsResponse: () -> RealEstateListingsResponse = {
    RealEstateListingsResponse(
        items = mutableListOf<RealEstateListingsApiItem>().apply {
            repeat(Random.nextInt(1, 10)) { realEstateId ->
                add(testRealEstateListingsApiItem().copy(id = realEstateId))
            }
        }
    )
}
