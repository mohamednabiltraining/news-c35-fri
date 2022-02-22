package com.route.newappc35fri.repos.news

import com.route.newappc35fri.Constants
import com.route.newappc35fri.api.Services
import com.route.newappc35fri.model.ArticlesItem

class NewsOnlineDataSourceImpl(val webServices: Services) : NewsOnlineDataSource {
    override suspend fun getNewsBySourceId(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = webServices.getNews(Constants.apiKey, sourceId)
            return result.articles
        } catch (ex: Exception) {
            throw ex;
        }
    }
}