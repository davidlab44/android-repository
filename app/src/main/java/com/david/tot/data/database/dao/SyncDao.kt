package com.david.tot.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.david.tot.domain.model.Article
import com.david.tot.domain.model.Sync

@Dao
interface SyncDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addOne(sync: Sync)

    @Query("SELECT * FROM SyncTable ORDER BY createdAt ASC")
    suspend fun getAll():List<Sync>

    /*
    @Query("SELECT * FROM syncTable ORDER BY createdAt ASC")
    suspend fun getAll():List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(recipes:List<Article>)

    @Query("DELETE FROM article_table")
    suspend fun deleteAll()

    @Query("SELECT * FROM article_table WHERE local_id = :local_id")
    suspend fun getById(local_id: Int): Article

    @Query("SELECT * FROM article_table WHERE articleDescription LIKE :recipeHash ORDER BY articleDescription ASC")
    fun getFiltered(recipeHash: String): List<Article>

    @Query("UPDATE article_table SET quantityToRestore = :quantityToRestoreValue WHERE local_id = :localIdArticle")
    suspend fun updateQuantity(localIdArticle:Int,quantityToRestoreValue:Int)
*/

}