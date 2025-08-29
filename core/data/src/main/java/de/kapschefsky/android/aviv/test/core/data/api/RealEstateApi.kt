package de.kapschefsky.android.aviv.test.core.data.api

import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingResponse
import retrofit2.Call
import retrofit2.http.GET

internal interface RealEstateApi {
    @GET("listings.json")
    fun getRealEstateListings(): Call<RealEstateListingResponse>
}