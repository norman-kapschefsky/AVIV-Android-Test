package de.kapschefsky.android.aviv.test.app.ui.components.realestate.listings

import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import de.kapschefsky.android.aviv.test.core.domain.common.testCoroutineDispatcherProvider
import de.kapschefsky.android.aviv.test.core.domain.usecases.RealEstateUseCases
import de.kapschefsky.android.aviv.test.core.model.RealEstateError
import de.kapschefsky.android.aviv.test.core.model.testRealEstateListingsItems
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class RealEstateListingsViewModelTest {
    private lateinit var mockRealEstateUseCases: RealEstateUseCases
    private lateinit var mockRealEstateListingsItemUiModelMapper: RealEstateListingsItemUiModelMapper
    private lateinit var viewModel: RealEstateListingsViewModel

    @Before
    fun before() {
        mockRealEstateUseCases = mockk()
        mockRealEstateListingsItemUiModelMapper = mockk()

        viewModel =
            RealEstateListingsViewModel(
                realEstateUseCases = mockRealEstateUseCases,
                coroutineDispatcherProvider = testCoroutineDispatcherProvider,
                realEstateListingsItemUiModelMapper = mockRealEstateListingsItemUiModelMapper,
            )
    }

    @Test
    fun `Load RealEstateListings by id returns RealEstateListingsUiState-RealEstate if obtaining was successful`() =
        runTest {
            viewModel.uiState.test {
                // Default state is "Loading"
                assertEquals(RealEstateListingsUiState.Loading, awaitItem())

                val testRealEstateListings = testRealEstateListingsItems(10)
                val expectedRealEstateListingsUiModels: List<RealEstateListingsItemUiModel> = mockk()

                coEvery { mockRealEstateUseCases.getRealEstateListings() } returns testRealEstateListings.right()

                every {
                    mockRealEstateListingsItemUiModelMapper.mapRealEstateListingsItemsToUiModels(testRealEstateListings)
                } returns expectedRealEstateListingsUiModels

                viewModel.loadRealEstateListings(coroutineScope = this)

                coVerify { mockRealEstateUseCases.getRealEstateListings() }
                verify { mockRealEstateListingsItemUiModelMapper.mapRealEstateListingsItemsToUiModels(testRealEstateListings) }

                val state = awaitItem()

                assertTrue(state is RealEstateListingsUiState.RealEstateListings)

                assertEquals(
                    RealEstateListingsUiState.RealEstateListings(expectedRealEstateListingsUiModels),
                    state as RealEstateListingsUiState.RealEstateListings,
                )
            }
        }

    @Test
    fun `Load RealEstateListings by id returns RealEstateListingsUiState-Error-Loading if obtaining failed`() =
        runTest {
            viewModel.uiState.test {
                // Default state is "Loading"
                assertEquals(RealEstateListingsUiState.Loading, awaitItem())

                val expectedRealEstateError: RealEstateError = RealEstateError.ApiError(responseCode = 500)

                coEvery { mockRealEstateUseCases.getRealEstateListings() } returns expectedRealEstateError.left()

                viewModel.loadRealEstateListings(coroutineScope = this)

                coVerify { mockRealEstateUseCases.getRealEstateListings() }
                verify(exactly = 0) { mockRealEstateListingsItemUiModelMapper.mapRealEstateListingsItemsToUiModels(any()) }

                val state = awaitItem()

                assertTrue(state is RealEstateListingsUiState.Error.Loading)

                assertEquals(
                    RealEstateListingsUiState.Error.Loading(expectedRealEstateError),
                    state as RealEstateListingsUiState.Error.Loading,
                )
            }
        }
}
