package de.kapschefsky.android.aviv.test.app.ui.navigation

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavKey
import de.kapschefsky.android.aviv.test.core.model.RealEstateId

fun SnapshotStateList<NavKey>.showRealEstateDetails(id: RealEstateId) {
    RealEstateDetailsNavKey(id).let { it ->
        if (contains(it)) {
            return@let
        }

        removeAll { it is RealEstateDetailsNavKey }
        add(it)
    }
}
