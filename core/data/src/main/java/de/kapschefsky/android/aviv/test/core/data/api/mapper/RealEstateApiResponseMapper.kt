package de.kapschefsky.android.aviv.test.core.data.api.mapper

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import de.kapschefsky.android.aviv.test.core.data.api.model.ApiError
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiListItem
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingResponse
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response

@Singleton
internal class RealEstateApiResponseMapper @Inject constructor() {

    fun mapListingResponse(
        response: Response<RealEstateListingResponse>)
    : Either<ApiError, List<RealEstateApiListItem>> =
        if (response.isSuccessful) {
            response.body()?.items?.right() ?: emptyList<RealEstateApiListItem>().right()
        } else {
            // TODO Error handling
            ApiError.General.left()
        }
}