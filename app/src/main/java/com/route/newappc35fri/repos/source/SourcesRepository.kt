package com.route.newappc35fri.repos.source

import com.route.newappc35fri.model.SourcesItem
import javax.xml.transform.Source

interface SourcesRepository {
    suspend fun getSources(category: String): List<SourcesItem?>?
}

interface SourcesOnlineDataSource {
    suspend fun getSources(category: String): List<SourcesItem?>?
}

interface SourcesOfflineDataSource {
    suspend fun updateSources(sources: List<SourcesItem?>?)
    suspend fun getSourcesByCategory(category: String): List<SourcesItem>
}