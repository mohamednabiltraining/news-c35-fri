package com.route.newappc35fri.repos.news

import com.route.newappc35fri.model.ArticlesItem

interface NewsRepository {
    suspend fun getNews(sourceId: String): List<ArticlesItem?>?
}

interface NewsOnlineDataSource {
    suspend fun getNewsBySourceId(sourceId: String)
            : List<ArticlesItem?>?
}