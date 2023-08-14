package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "ReportableTable")
data class Reportable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    @ColumnInfo(name = "photo") val photo: String ="",
    @ColumnInfo(name = "description") val description: String = "",
    )

fun Reportable.toDomain() = Reportable(local_id,photo,description)
fun Reportable.toDatabase() = Reportable(local_id=local_id, photo=photo, description=description)