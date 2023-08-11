package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "syncTable")
data class Sync (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    //@ColumnInfo(name = "remote_id") val id: Int,
    @ColumnInfo(name = "consumptionId") val consumptionId: Int,
    @ColumnInfo(name = "sharedPreferenceName") val sharedPreferenceName: String,
    @ColumnInfo(name = "objectType") val objectType: String,
    @ColumnInfo(name = "createdAt") val createdAt: String = "",
    )
fun Sync.toDomain() = Sync(local_id,consumptionId,sharedPreferenceName,objectType,createdAt)
fun Sync.toDatabase() = Sync(local_id=local_id,consumptionId=consumptionId,sharedPreferenceName=sharedPreferenceName,objectType=objectType,createdAt=createdAt)