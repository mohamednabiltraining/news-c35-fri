package com.route.newappc35fri.repos.source

import com.route.newappc35fri.Constants
import com.route.newappc35fri.api.Services
import com.route.newappc35fri.model.SourcesItem
import java.lang.Exception

class SourcesOnlineDataSourceImpl(val webServices: Services) :
    SourcesOnlineDataSource {
    override suspend fun getSources(category: String): List<SourcesItem?>? {
        try {
            val result = webServices.getNewsSources(Constants.apiKey, category);
            return result.sources;
        } catch (ex: Exception) {
            throw ex;
        }
    }
}