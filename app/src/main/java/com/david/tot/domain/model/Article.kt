package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_table")
data class Article (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    //@ColumnInfo(name = "remote_id") val id: Int,
    @ColumnInfo(name = "articleCode") val articleCode: String,
    @ColumnInfo(name = "articleDescription") val articleDescription: String,
    @ColumnInfo(name = "unitOfMeasure") val unitOfMeasure: String,
    @ColumnInfo(name = "quantityAvailable") val quantityAvailable: Double,
    @ColumnInfo(name = "quantityToStock") val quantityToStock: Double,
    @ColumnInfo(name = "quantityToRestore") val quantityToRestore: Int = 0
    /*
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Int = 0,
    @ColumnInfo(name = "requested_amount") val requested_amount: Int = 0,
    @ColumnInfo(name = "is_milligram") val is_milligram: Int = 0,
    @ColumnInfo(name = "is_unit") val is_unit: Int = 0
    */
    )
fun Article.toDomain() = Article(local_id,articleCode,articleDescription,unitOfMeasure,quantityAvailable,quantityToStock,quantityToRestore)
fun Article.toDatabase() = Article(local_id=local_id, articleCode=articleCode, articleDescription=articleDescription,unitOfMeasure=unitOfMeasure,quantityAvailable=quantityAvailable,quantityToStock=quantityToStock,quantityToRestore=quantityToRestore)