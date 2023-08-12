package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "SyncTable")
data class Sync (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    @ColumnInfo(name = "idSync") val idSync: Int = 0,
    @ColumnInfo(name = "dataType") val dataType: String,
    @ColumnInfo(name = "createdAt") val createdAt: String = "",
    )
fun Sync.toDomain() = Sync(local_id,idSync,dataType,createdAt)
fun Sync.toDatabase() = Sync(local_id=local_id,idSync=idSync,dataType=dataType,createdAt=createdAt)