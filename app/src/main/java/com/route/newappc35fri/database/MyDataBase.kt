package com.route.newappc35fri.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.route.newappc35fri.model.SourcesItem


@Database(entities = [SourcesItem::class], version = 1, exportSchema = false)
abstract class MyDataBase : RoomDatabase() {
    abstract fun SourcesDao(): SourcesDao

    companion object {
        var database: MyDataBase? = null
        const val DATABASE_NAME = "newsdatabase";
        fun init(context: Context) {
            if (database == null) {
                database = Room.databaseBuilder(
                    context, MyDataBase::class.java, DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }

        fun getInstance(): MyDataBase {
            return database!!
        }
    }
}