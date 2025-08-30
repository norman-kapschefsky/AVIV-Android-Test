package de.kapschefsky.android.aviv.test.app.ui.components.realestate.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import de.kapschefsky.android.aviv.test.core.model.RealEstateId

@Composable
fun RealEstateDetailsUi(
    id: RealEstateId,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier =
            modifier
                .background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {
        Text(text = "RealEstate #$id")
    }
}
