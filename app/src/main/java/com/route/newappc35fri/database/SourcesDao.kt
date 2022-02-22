package com.route.newappc35fri.database

import androidx.room.*
import com.route.newappc35fri.model.SourcesItem

@Dao
interface SourcesDao {

    @Query("select * from SourcesItem")
    suspend fun getSources(): List<SourcesItem?>

    @Query("select * from SourcesItem where category=:category")
    suspend fun getSourcesByCategoryId(category: String): List<SourcesItem>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateSources(sources: List<SourcesItem?>?)
}