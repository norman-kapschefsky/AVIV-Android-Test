package de.kapschefsky.android.aviv.test.app.ui.components.common

import androidx.annotation.FloatRange
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.Scene
import androidx.navigation3.ui.SceneStrategy
import androidx.window.core.layout.WindowSizeClass.Companion.WIDTH_DP_MEDIUM_LOWER_BOUND
import de.kapschefsky.android.aviv.test.core.model.ONE
import de.kapschefsky.android.aviv.test.core.model.TWO

/**
 * A [Scene] that displays two panes (left and right) if width reaches 600dp and above.
 */
class TwoPaneScene<T : Any>(
    override val key: Any,
    override val previousEntries: List<NavEntry<T>>,
    val firstEntry: NavEntry<T>,
    val secondEntry: NavEntry<T>,
    @FloatRange(from = 0.0, to = 1.0, fromInclusive = false) firstEntryWeight: Float = 0.5f,
) : Scene<T> {
    override val entries: List<NavEntry<T>> = listOf(firstEntry, secondEntry)

    override val content: @Composable (() -> Unit) = {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.weight(firstEntryWeight)) {
                firstEntry.Content()
            }

            Column(modifier = Modifier.weight(ONE.toFloat() - firstEntryWeight)) {
                secondEntry.Content()
            }
        }
    }

    companion object {
        const val TWO_PANE_KEY = "TwoPane"

        fun twoPaneEnabledMetaData() = mapOf(TWO_PANE_KEY to true)
    }
}

class TwoPaneSceneStrategy<T : Any> : SceneStrategy<T> {
    @Composable
    override fun calculateScene(
        entries: List<NavEntry<T>>,
        onBack: (Int) -> Unit,
    ): Scene<T>? {
        val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

        // Only twoPane if at least 600dp width are reached.
        if (!windowSizeClass.isWidthAtLeastBreakpoint(WIDTH_DP_MEDIUM_LOWER_BOUND)) {
            return null
        }

        val lastTwoEntries = entries.takeLast(TWO)
        val isTwoPane = lastTwoEntries.size == TWO && lastTwoEntries.all { it.metadata.containsKey(TwoPaneScene.TWO_PANE_KEY) }

        return if (isTwoPane) {
            val firstEntry = lastTwoEntries.first()
            val secondEntry = lastTwoEntries.last()

            TwoPaneScene(
                key = firstEntry.contentKey to secondEntry.contentKey,
                previousEntries = entries.dropLast(ONE),
                firstEntry = firstEntry,
                secondEntry = secondEntry,
            )
        } else {
            null
        }
    }
}
