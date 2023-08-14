package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Blob

@Entity(tableName = "ReportableTable")
data class Reportable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    @ColumnInfo(name = "photo") val photo: ByteArray,
    @ColumnInfo(name = "description") val description: String = "",
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Reportable

        if (!photo.contentEquals(other.photo)) return false

        return true
    }

    override fun hashCode(): Int {
        return photo.contentHashCode()
    }
}

fun Reportable.toDomain() = Reportable(local_id,photo,description)
fun Reportable.toDatabase() = Reportable(local_id=local_id, photo=photo, description=description)