package com.route.newappc35fri.repos.source

import com.route.newappc35fri.NetworkHandler
import com.route.newappc35fri.api.Services
import com.route.newappc35fri.database.MyDataBase
import com.route.newappc35fri.repos.source.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SourcesModule {

    @Provides
    fun provideOnlineDataSource(webServices: Services)
            : SourcesOnlineDataSource {
        return SourcesOnlineDataSourceImpl(webServices)
    }

    @Provides
    fun provideOfflineDataSource(dataBase: MyDataBase): SourcesOfflineDataSource {
        return SourceOfflineDataSourceImpl(dataBase)
    }

    @Singleton
    @Provides
    fun provideDataBase(): MyDataBase {
        return MyDataBase.getInstance();
    }

    @Provides
    fun provideSourcesRepo(
        onlineDataSource: SourcesOnlineDataSource,
        offlineDataSource: SourcesOfflineDataSource,
        networkHandler: NetworkHandler
    ): SourcesRepository {
        return SourcesRepositoryImpl(
            onlineDataSource,
            offlineDataSource, networkHandler
        )
    }

}