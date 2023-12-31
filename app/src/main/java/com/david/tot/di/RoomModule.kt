package com.david.tot.di

import android.content.Context
import androidx.room.Room
import com.david.tot.data.database.RecipeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val RECIPE_DATABASE_NAME = "product_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context, RecipeDatabase::class.java, RECIPE_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideRecipeDao(db: RecipeDatabase) = db.getRecipeDao()

    @Singleton
    @Provides
    fun provideDrugsDeliveryConsumerViewHeaderDao(db: RecipeDatabase) = db.getAuthenticableDao()

    @Singleton
    @Provides
    fun provideSyncDao(db: RecipeDatabase) = db.getSyncDao()

    @Singleton
    @Provides
    fun providePreDao(db: RecipeDatabase) = db.getPreDao()

    @Singleton
    @Provides
    fun provideSynConsumibleDao(db: RecipeDatabase) = db.getSyncConsumibleDao()

    @Singleton
    @Provides
    fun provideReportableDao(db: RecipeDatabase) = db.getReportableDao()

    @Singleton
    @Provides
    fun provideReloadableDao(db: RecipeDatabase) = db.getReloadableDao()

    @Singleton
    @Provides
    fun provideSyncReloadableDao(db: RecipeDatabase) = db.getSyncReloadableDao()

    @Singleton
    @Provides
    fun provideConfirmableDao(db: RecipeDatabase) = db.getConfirmableDao()

    @Singleton
    @Provides
    fun provideSpendableDao(db: RecipeDatabase) = db.getSpendableDao()

    @Singleton
    @Provides
    fun provideSpotableDao(db: RecipeDatabase) = db.getSpotableDao()
}

