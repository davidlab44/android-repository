package com.david.tot.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.tot.data.database.dao.AuthenticableDao
import com.david.tot.data.database.dao.ArticleDao
import com.david.tot.data.database.dao.PreDao
import com.david.tot.data.database.dao.ReportableDao
import com.david.tot.data.database.dao.SyncConsumibleDao
import com.david.tot.data.database.dao.SyncDao
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Authenticable
import com.david.tot.domain.model.Pre
import com.david.tot.domain.model.Reportable
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncConsumible

@Database(entities = [Article::class, Authenticable::class, Sync::class, Pre::class, SyncConsumible::class, Reportable::class], version = 32)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun getRecipeDao(): ArticleDao

    abstract fun getAuthenticableDao(): AuthenticableDao

    abstract fun getSyncDao(): SyncDao

    abstract fun getPreDao(): PreDao

    abstract fun getSyncConsumibleDao(): SyncConsumibleDao

    abstract fun getReportableDao(): ReportableDao
}












/*
package com.david.tot.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.tot.data.database.dao.RecipeDao
import com.david.tot.domain.model.Product


@Database(entities = [Product::class], version = 13)
abstract class RecipeDatabase: RoomDatabase() {

    abstract fun getRecipeDao(): RecipeDao
}

 */
