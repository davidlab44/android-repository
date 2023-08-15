package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "ReportableTable")
data class Reportable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId") val localId: Int = 0,
    @ColumnInfo(name = "generatedId") val generatedId: Long = 0,
    @ColumnInfo(name = "photo") val photo: String ="",
    @ColumnInfo(name = "description") val description: String = "",
    )

fun Reportable.toDomain() = Reportable(localId,generatedId,photo,description)
fun Reportable.toDatabase() = Reportable(localId=localId,generatedId-generatedId, photo=photo, description=description)