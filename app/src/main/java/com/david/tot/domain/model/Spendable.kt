package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//@Entity(tableName = "Article")
data class Spendable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    @ColumnInfo(name = "name") val name: String
    )
/*
fun Article.toDomain() = Article(local_id,articleCode,articleDescription,unitOfMeasure,quantityAvailable,quantityToStock,quantityToRestore)
fun Article.toDatabase() = Article(local_id=local_id, articleCode=articleCode, articleDescription=articleDescription,unitOfMeasure=unitOfMeasure,quantityAvailable=quantityAvailable,quantityToStock=quantityToStock,quantityToRestore=quantityToRestore)

 */