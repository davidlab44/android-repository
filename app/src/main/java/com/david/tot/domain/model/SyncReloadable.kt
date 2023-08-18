package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "SyncReloadableTable")
data class SyncReloadable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId") val localId: Int = 0,
    @ColumnInfo(name = "objectId") val objectId: Int,
    @ColumnInfo(name = "consumptionDetailId") val consumptionDetailId: Int=0,
    @ColumnInfo(name = "consumptionId") val consumptionId: Int=0,
    @ColumnInfo(name = "articleCode") val articleCode: String="",
    @ColumnInfo(name = "quantity") val quantity: Double=0.0,
    @ColumnInfo(name = "unitOfMeasure") val unitOfMeasure: String="",
    @ColumnInfo(name = "creationDate") val creationDate: String="2023-01-14T09:15:07.76",
    @ColumnInfo(name = "delivered") val delivered: Int=0
)
fun SyncReloadable.toDomain() = SyncReloadable(localId,objectId,consumptionDetailId,consumptionId,articleCode,quantity,unitOfMeasure,creationDate,delivered)
fun SyncReloadable.toDatabase() = SyncReloadable(localId=localId,objectId=objectId,consumptionDetailId=consumptionDetailId,consumptionId=consumptionId,articleCode=articleCode,quantity=quantity,unitOfMeasure=unitOfMeasure,creationDate=creationDate,delivered=delivered)
//fun SyncReloadable.toSyncReloadable() = SyncReloadable(consumptionDetailId=consumptionDetailId,consumptionId=consumptionId,articleCode=articleCode,quantity=quantity,unitOfMeasure=unitOfMeasure,creationDate=creationDate,delivered=delivered)