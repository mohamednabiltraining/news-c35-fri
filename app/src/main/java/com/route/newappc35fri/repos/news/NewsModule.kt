package com.route.newappc35fri.repos.news

import com.route.newappc35fri.api.Services
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object NewsModule {

    @Provides
    fun provideNewsRepo(newsOnlineDataSource: NewsOnlineDataSource): NewsRepository {
        return NewsRepositoryImpl(newsOnlineDataSource)
    }

    @Provides
    fun provideNewsOnlineDataSource(webServices: Services): NewsOnlineDataSource {
        return NewsOnlineDataSourceImpl(webServices)
    }
}