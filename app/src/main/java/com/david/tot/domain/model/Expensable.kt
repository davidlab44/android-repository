package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ExpensableTable")
data class Expensable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "photo") val photo: String ="",
    @ColumnInfo(name = "description") val description: String = "",
)

fun Expensable.toDomain() = Expensable(local_id,photo,description)
fun Expensable.toDatabase() = Expensable(local_id=local_id,photo=photo,description=description)