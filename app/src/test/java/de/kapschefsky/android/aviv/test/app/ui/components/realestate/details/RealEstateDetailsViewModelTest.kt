package de.kapschefsky.android.aviv.test.app.ui.components.realestate.details

import app.cash.turbine.test
import arrow.core.left
import arrow.core.right
import de.kapschefsky.android.aviv.test.core.domain.common.testCoroutineDispatcherProvider
import de.kapschefsky.android.aviv.test.core.domain.usecases.RealEstateUseCases
import de.kapschefsky.android.aviv.test.core.model.ONE
import de.kapschefsky.android.aviv.test.core.model.RealEstateError
import de.kapschefsky.android.aviv.test.core.model.testRealEstate
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

class RealEstateDetailsViewModelTest {
    private lateinit var mockRealEstateUseCases: RealEstateUseCases
    private lateinit var mockRealEstateDetailsUiModelMapper: RealEstateDetailsUiModelMapper
    private lateinit var viewModel: RealEstateDetailsViewModel

    @Before
    fun before() {
        mockRealEstateUseCases = mockk()
        mockRealEstateDetailsUiModelMapper = mockk()

        viewModel =
            RealEstateDetailsViewModel(
                realEstateUseCases = mockRealEstateUseCases,
                coroutineDispatcherProvider = testCoroutineDispatcherProvider,
                realEstateDetailsUiModelMapper = mockRealEstateDetailsUiModelMapper,
            )
    }

    @Test
    fun `Load RealEstateDetails by id returns RealEstateDetailsUiState-RealEstate if obtaining was successful`() =
        runTest {
            viewModel.uiState.test {
                // Default state is "Loading"
                assertEquals(RealEstateDetailsUiState.Loading, awaitItem())

                val testRealEstate = testRealEstate()
                val expectedRealEstateDetailsUiModel: RealEstateDetailsUiModel = mockk()

                coEvery { mockRealEstateUseCases.getRealEstate(testRealEstate.id) } returns testRealEstate.right()
                every { mockRealEstateDetailsUiModelMapper.mapRealEstateToDetailsUiModel(testRealEstate) } returns
                    expectedRealEstateDetailsUiModel

                viewModel.loadRealEstateDetails(
                    id = testRealEstate.id,
                    coroutineScope = this,
                )

                coVerify { mockRealEstateUseCases.getRealEstate(testRealEstate.id) }
                verify { mockRealEstateDetailsUiModelMapper.mapRealEstateToDetailsUiModel(testRealEstate) }

                val state = awaitItem()

                assertTrue(state is RealEstateDetailsUiState.RealEstate)

                assertEquals(
                    RealEstateDetailsUiState.RealEstate(expectedRealEstateDetailsUiModel),
                    state as RealEstateDetailsUiState.RealEstate,
                )
            }
        }

    @Test
    fun `Load RealEstateDetails by id returns RealEstateDetailsUiState-Error-Loading if obtaining failed`() =
        runTest {
            viewModel.uiState.test {
                // Default state is "Loading"
                assertEquals(RealEstateDetailsUiState.Loading, awaitItem())

                val testRealEstateId = ONE
                val expectedRealEstateError: RealEstateError = RealEstateError.ApiError(responseCode = 500)

                coEvery { mockRealEstateUseCases.getRealEstate(testRealEstateId) } returns expectedRealEstateError.left()

                viewModel.loadRealEstateDetails(
                    id = testRealEstateId,
                    coroutineScope = this,
                )

                coVerify { mockRealEstateUseCases.getRealEstate(testRealEstateId) }
                verify(exactly = 0) { mockRealEstateDetailsUiModelMapper.mapRealEstateToDetailsUiModel(any()) }

                val state = awaitItem()

                assertTrue(state is RealEstateDetailsUiState.Error.Loading)

                assertEquals(
                    RealEstateDetailsUiState.Error.Loading(expectedRealEstateError),
                    state as RealEstateDetailsUiState.Error.Loading,
                )
            }
        }
}
