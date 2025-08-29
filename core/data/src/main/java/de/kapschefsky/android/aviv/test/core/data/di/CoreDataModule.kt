package de.kapschefsky.android.aviv.test.core.data.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import de.kapschefsky.android.aviv.test.core.data.R
import de.kapschefsky.android.aviv.test.core.data.api.RealEstateApi
import de.kapschefsky.android.aviv.test.core.data.api.RealEstateRepositoryImpl
import de.kapschefsky.android.aviv.test.core.data.repository.RealEstateRepository
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CoreDataModule {

    @Binds
    abstract fun bindRealEstateRepository(repository: RealEstateRepositoryImpl): RealEstateRepository

    companion object {

        @Singleton
        @Provides
        internal fun provideRetrofit(
            app: Application,
        ): Retrofit =
            Retrofit.Builder()
                .baseUrl(app.getString(R.string.apiBaseUrl))
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        @Singleton
        @Provides
        internal fun provideRealEstateApi(
            retrofit: Retrofit
        ): RealEstateApi = retrofit.create(RealEstateApi::class.java)
    }
}