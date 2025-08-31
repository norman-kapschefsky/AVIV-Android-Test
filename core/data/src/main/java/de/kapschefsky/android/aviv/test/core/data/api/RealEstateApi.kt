package de.kapschefsky.android.aviv.test.core.data.api

import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

internal interface RealEstateApi {
    @GET("listings.json")
    fun getRealEstateListings(): Call<RealEstateListingsResponse>

    @GET("listings/{id}.json")
    fun getRealEstateListing(@Path("id") id: Int): Call<RealEstateApiModel>
}