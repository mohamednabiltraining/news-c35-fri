package com.route.newappc35fri.repos.source

import com.route.newappc35fri.database.MyDataBase
import com.route.newappc35fri.model.SourcesItem

class SourceOfflineDataSourceImpl(val myDataBase: MyDataBase) :
    SourcesOfflineDataSource {
    override suspend fun getSourcesByCategory(category: String): List<SourcesItem> {
        return myDataBase.SourcesDao().getSourcesByCategoryId(category)
    }

    override suspend fun updateSources(sources: List<SourcesItem?>?) {
        myDataBase.SourcesDao().updateSources(sources)
    }
}