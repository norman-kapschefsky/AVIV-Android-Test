package de.kapschefsky.android.aviv.test.core.data.api.mapper

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import de.kapschefsky.android.aviv.test.core.data.api.model.ApiError
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingsApiItem
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateApiModel
import de.kapschefsky.android.aviv.test.core.data.api.model.RealEstateListingsResponse
import javax.inject.Inject
import javax.inject.Singleton
import retrofit2.Response

@Singleton
internal class RealEstateApiResponseMapper @Inject constructor() {

    fun mapRealEstateListingsResponse(
        response: Response<RealEstateListingsResponse>
    ): Either<ApiError, List<RealEstateListingsApiItem>> =
        if (response.isSuccessful) {
            response.body()?.items?.right() ?: ApiError.EmptyResponse.left()
        } else {
            ApiError.General(responseCode = response.code()).left()
        }

    fun mapRealEstateApiModelResponse(
        response: Response<RealEstateApiModel>
    ): Either<ApiError, RealEstateApiModel> =
        if (response.isSuccessful) {
            response.body()?.right() ?: ApiError.EmptyResponse.left()
        } else {
            ApiError.General(responseCode = response.code()).left()
        }
}