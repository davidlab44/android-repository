package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Article")
data class Article (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    //@ColumnInfo(name = "remote_id") val id: Int,
    @ColumnInfo(name = "articleCode") val articleCode: String,
    @ColumnInfo(name = "articleDescription") val articleDescription: String,
    @ColumnInfo(name = "notes") var notes: String?="",
    @ColumnInfo(name = "unitOfMeasure") val unitOfMeasure: String,
    @ColumnInfo(name = "quantityAvailable") var quantityAvailable: Double,
    @ColumnInfo(name = "quantityToStock") val quantityToStock: Double,
    @ColumnInfo(name = "quantityToRestore") val quantityToRestore: Int = 0,
    @ColumnInfo(name = "consumedQuantity") var consumedQuantity: Int = 0,
    @ColumnInfo(name = "isControlled") var isControlled: Boolean,
    )
fun Article.toDomain() = Article(local_id,articleCode,articleDescription,notes,unitOfMeasure,quantityAvailable,quantityToStock,quantityToRestore,isControlled=isControlled)
fun Article.toDatabase() = Article(local_id=local_id, articleCode=articleCode, articleDescription=articleDescription,notes=notes,unitOfMeasure=unitOfMeasure,quantityAvailable=quantityAvailable,quantityToStock=quantityToStock,quantityToRestore=quantityToRestore,isControlled=isControlled)