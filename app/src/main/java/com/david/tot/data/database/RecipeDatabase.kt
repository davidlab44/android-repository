package com.david.tot.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.tot.data.database.dao.DrugsDeliveryConsumerViewHeaderDao
import com.david.tot.data.database.dao.ArticleDao
import com.david.tot.data.database.dao.SyncDao
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader
import com.david.tot.domain.model.Sync

@Database(entities = [Article::class, DrugsDeliveryConsumerViewHeader::class, Sync::class], version = 22)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun getRecipeDao(): ArticleDao

    abstract fun getDrugsDeliveryConsumerViewHeaderDao(): DrugsDeliveryConsumerViewHeaderDao

    abstract fun syncDao(): SyncDao
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
