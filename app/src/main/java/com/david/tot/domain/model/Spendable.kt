package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SpendableTable")
data class Spendable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId") val localId: Int = 0,
    @ColumnInfo(name = "generatedId") val generatedId: Long = 0,
    @ColumnInfo(name = "photo") val photo: String ="",
    @ColumnInfo(name = "description") val description: String = "",
)

fun Spendable.toDomain() = Spendable(localId,generatedId,photo,description)

fun Spendable.toDatabase() = Spendable(localId=localId,generatedId-generatedId, photo=photo, description=description)