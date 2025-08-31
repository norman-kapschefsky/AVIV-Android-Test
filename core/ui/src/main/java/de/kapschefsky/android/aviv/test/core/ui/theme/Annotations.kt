package de.kapschefsky.android.aviv.test.core.ui.theme

import android.content.res.Configuration
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "light",
    showBackground = true,
    backgroundColor = 0xfff9f9ff,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
annotation class ThemeLightPreview

@Preview(
    name = "dark",
    showBackground = true,
    backgroundColor = 0xff121317,
    uiMode = UI_MODE_NIGHT_YES
)
annotation class ThemeDarkPreview