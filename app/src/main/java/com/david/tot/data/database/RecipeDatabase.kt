package com.david.tot.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.david.tot.data.database.dao.DrugsDeliveryConsumerViewHeaderDao
import com.david.tot.data.database.dao.RecipeDao
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.DrugsDeliveryConsumerViewHeader


@Database(entities = [Article::class, DrugsDeliveryConsumerViewHeader::class], version = 16)
abstract class RecipeDatabase: RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao

    abstract fun getDrugsDeliveryConsumerViewHeaderDao(): DrugsDeliveryConsumerViewHeaderDao
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
