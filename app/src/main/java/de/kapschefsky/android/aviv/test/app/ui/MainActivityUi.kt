package de.kapschefsky.android.aviv.test.app.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.navEntryDecorator
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import de.kapschefsky.android.aviv.test.R
import de.kapschefsky.android.aviv.test.app.ui.components.common.TwoPaneScene
import de.kapschefsky.android.aviv.test.app.ui.components.common.TwoPaneSceneStrategy
import de.kapschefsky.android.aviv.test.app.ui.components.realestate.details.RealEstateDetailsUi
import de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings.RealEstateListingsUi
import de.kapschefsky.android.aviv.test.app.ui.navigation.RealEstateDetailsNavKey
import de.kapschefsky.android.aviv.test.app.ui.navigation.RealEstateListingsNavKey
import de.kapschefsky.android.aviv.test.app.ui.navigation.showRealEstateDetails

@OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainActivityUi() {
    val localNavSharedTransitionScope: ProvidableCompositionLocal<SharedTransitionScope> =
        compositionLocalOf {
            throw IllegalStateException(
                "Unexpected access to LocalNavSharedTransitionScope. You must provide a " +
                    "SharedTransitionScope from a call to SharedTransitionLayout() or " +
                    "SharedTransitionScope()",
            )
        }

    val sharedEntryInSceneNavEntryDecorator =
        navEntryDecorator<NavKey> { entry ->
            with(localNavSharedTransitionScope.current) {
                Box(
                    Modifier.sharedElement(
                        rememberSharedContentState(entry.contentKey),
                        animatedVisibilityScope = LocalNavAnimatedContentScope.current,
                    ),
                ) {
                    entry.Content()
                }
            }
        }

    val backStack = rememberNavBackStack(RealEstateListingsNavKey)
    val twoPaneStrategy = remember { TwoPaneSceneStrategy<Any>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.toolbar_title),
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.headlineLarge,
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
            )
        },
        content = { contentPadding ->
            SharedTransitionLayout(
                modifier =
                    Modifier.padding(
                        start = contentPadding.calculateStartPadding(LocalLayoutDirection.current),
                        top = contentPadding.calculateTopPadding(),
                        end = contentPadding.calculateEndPadding(LocalLayoutDirection.current),
                    ),
            ) {
                CompositionLocalProvider(localNavSharedTransitionScope provides this) {
                    NavDisplay(
                        backStack = backStack,
                        onBack = { keysToRemove -> repeat(keysToRemove) { backStack.removeLastOrNull() } },
                        entryDecorators =
                            listOf(
                                sharedEntryInSceneNavEntryDecorator,
                                rememberSceneSetupNavEntryDecorator(),
                                rememberSavedStateNavEntryDecorator(),
                            ),
                        sceneStrategy = twoPaneStrategy,
                        entryProvider =
                            entryProvider {
                                entry<RealEstateListingsNavKey>(
                                    metadata = TwoPaneScene.Companion.twoPaneEnabledMetaData(),
                                ) {
                                    RealEstateListingsUi(
                                        onRealEstateItemClicked = { realEstate ->
                                            backStack.showRealEstateDetails(
                                                id = realEstate.id,
                                                headlineText = realEstate.name,
                                            )
                                        },
                                        modifier = Modifier.fillMaxSize(),
                                    )
                                }

                                entry<RealEstateDetailsNavKey>(
                                    metadata = TwoPaneScene.Companion.twoPaneEnabledMetaData(),
                                ) { realEstateDetailsNavKey ->
                                    RealEstateDetailsUi(
                                        id = realEstateDetailsNavKey.id,
                                        headlineText = realEstateDetailsNavKey.headlineText,
                                        modifier = Modifier.fillMaxSize(),
                                    )
                                }
                            },
                    )
                }
            }
        },
    )
}
