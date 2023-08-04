package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Article

@Dao
interface RecipeDao {

    @Query("SELECT * FROM article_table")
    suspend fun getAllRecipes():List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes:List<Article>)

    @Query("DELETE FROM article_table")
    suspend fun deleteAllRecipes()

    @Query("SELECT * FROM article_table WHERE local_id = :local_id")
    suspend fun getArticleById(local_id: Int): Article

    /*
    @Query("SELECT * FROM article_table WHERE name LIKE :recipeHash ORDER BY name DESC LIMIT 10")
    fun getFilteredRecipes(recipeHash: String): List<Article>

     */

    @Query("UPDATE article_table SET quantityToRestore = :quantityToRestoreValue WHERE local_id = :localIdArticle")
    suspend fun updateQuantity(localIdArticle:Int,quantityToRestoreValue:Int)


}