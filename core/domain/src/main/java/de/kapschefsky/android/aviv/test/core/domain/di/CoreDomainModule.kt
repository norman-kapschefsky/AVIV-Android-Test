package de.kapschefsky.android.aviv.test.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.kapschefsky.android.aviv.test.core.domain.common.CoroutineDispatcherProvider
import de.kapschefsky.android.aviv.test.core.domain.common.defaultCoroutineDispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CoreDomainModule {

    companion object {

        @Singleton
        @Provides
        internal fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider =
            defaultCoroutineDispatcherProvider
    }
}