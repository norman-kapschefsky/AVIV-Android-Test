package de.kapschefsky.android.aviv.test.core.data.api.model

sealed class ApiError {
    data object General: ApiError()
}