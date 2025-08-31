package de.kapschefsky.android.aviv.test.core.data.api.model

sealed class ApiError {
    data class General(val responseCode: Int): ApiError()

    data object EmptyResponse: ApiError()
}