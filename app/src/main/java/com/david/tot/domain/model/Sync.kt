package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "syncTable")
data class Sync (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    //@ColumnInfo(name = "remote_id") val id: Int,
    @ColumnInfo(name = "objectId") val objectId: String,
    @ColumnInfo(name = "objectType") val objectType: String,
    @ColumnInfo(name = "createdAt") val createdAt: Int = 0,

    /*
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "price") val price: Int = 0,
    @ColumnInfo(name = "requested_amount") val requested_amount: Int = 0,
    @ColumnInfo(name = "is_milligram") val is_milligram: Int = 0,
    @ColumnInfo(name = "is_unit") val is_unit: Int = 0
    */
    )
fun Sync.toDomain() = Sync(local_id,objectId,objectType,createdAt)
fun Sync.toDatabase() = Sync(local_id=local_id, objectId=objectId, objectType=objectType,createdAt=createdAt)