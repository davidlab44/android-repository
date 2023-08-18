package com.david.tot.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.tot.data.database.dao.AuthenticableDao
import com.david.tot.data.database.dao.ConsumibleDao
import com.david.tot.data.database.dao.PreDao
import com.david.tot.data.database.dao.ReloadableDao
import com.david.tot.data.database.dao.ReportableDao
import com.david.tot.data.database.dao.SyncConsumibleDao
import com.david.tot.data.database.dao.SyncDao
import com.david.tot.data.database.dao.SyncReloadableDao
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Authenticable
import com.david.tot.domain.model.Pre
import com.david.tot.domain.model.Reloadable
import com.david.tot.domain.model.Reportable
import com.david.tot.domain.model.Sync
import com.david.tot.domain.model.SyncConsumible
import com.david.tot.domain.model.SyncReloadable

@Database(entities = [Article::class, Authenticable::class, Sync::class, Pre::class, SyncConsumible::class, Reportable::class, Reloadable::class, SyncReloadable::class], version = 42)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun getRecipeDao(): ConsumibleDao

    abstract fun getAuthenticableDao(): AuthenticableDao

    abstract fun getSyncDao(): SyncDao

    abstract fun getPreDao(): PreDao

    abstract fun getSyncConsumibleDao(): SyncConsumibleDao

    abstract fun getReportableDao(): ReportableDao

    abstract fun getReloadableDao(): ReloadableDao

    abstract fun getSyncReloadableDao(): SyncReloadableDao
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
