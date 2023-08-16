package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Article

@Dao
interface ConsumibleDao {
    @Query("SELECT * FROM Article ORDER BY articleDescription ASC")
    suspend fun getAll():List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes:List<Article>)

    @Query("DELETE FROM Article")
    suspend fun deleteAll()

    @Query("SELECT * FROM Article WHERE local_id = :local_id")
    suspend fun getById(local_id: Int): Article

    @Query("SELECT * FROM Article WHERE articleDescription LIKE :recipeHash ORDER BY articleDescription ASC")
    fun getFiltered(recipeHash: String): List<Article>

    @Query("UPDATE Article SET consumedQuantity = :consumibleNewQuantity WHERE local_id = :localIdArticle")
    suspend fun updateConsumedQuantity(localIdArticle:Int, consumibleNewQuantity:Int):Int
}