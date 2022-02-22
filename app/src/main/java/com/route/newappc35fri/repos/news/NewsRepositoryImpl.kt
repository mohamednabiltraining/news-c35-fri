package com.route.newappc35fri.repos.news

import com.route.newappc35fri.model.ArticlesItem
import java.lang.Exception

class NewsRepositoryImpl(val newsOnlineDataSource: NewsOnlineDataSource) : NewsRepository {
    override suspend fun getNews(sourceId: String): List<ArticlesItem?>? {
        try {
            val result = newsOnlineDataSource.getNewsBySourceId(sourceId);
            return result;
        } catch (ex: Exception) {
            throw ex;
        }
    }
}