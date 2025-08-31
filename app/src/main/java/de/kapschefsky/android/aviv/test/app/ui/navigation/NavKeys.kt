package de.kapschefsky.android.aviv.test.app.ui.navigation

import androidx.navigation3.runtime.NavKey
import de.kapschefsky.android.aviv.test.core.model.RealEstateId
import kotlinx.serialization.Serializable

@Serializable
object RealEstateListingsNavKey : NavKey

@Serializable
data class RealEstateDetailsNavKey(
    val id: RealEstateId,
    val headlineText: String,
) : NavKey
