package de.kapschefsky.android.aviv.test.app.ui.mapper

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AreaUiMapper
    @Inject
    constructor() {
        fun mapToDisplayableArea(
            areaValue: Int?,
            areaMeasureUnit: String?,
        ): String? =
            if (areaValue == null || areaMeasureUnit == null) {
                null
            } else {
                "$areaValue $areaMeasureUnit"
            }
    }
