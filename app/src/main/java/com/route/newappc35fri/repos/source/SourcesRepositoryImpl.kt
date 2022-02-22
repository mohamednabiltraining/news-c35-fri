package com.route.newappc35fri.repos.source

import com.route.newappc35fri.NetworkHandler
import com.route.newappc35fri.model.SourcesItem
import java.lang.Exception

class SourcesRepositoryImpl(
    val onlineDataSource: SourcesOnlineDataSource,
    val offlineDataSource: SourcesOfflineDataSource,
    val networkHandler: NetworkHandler
) : SourcesRepository {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            if (networkHandler.isOnline()) {
                val result = onlineDataSource.getSources(category);
                offlineDataSource.updateSources(result)
                return result;
            }
            val res = offlineDataSource.getSourcesByCategory(category)
            return res;
        } catch (ex: Exception) {
            return offlineDataSource.getSourcesByCategory(category)
        }
    }
}