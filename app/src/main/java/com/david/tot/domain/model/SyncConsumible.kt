package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.david.tot.util.Dates
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "SyncConsumibleTable")
data class SyncConsumible (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId") val localId: Int = 0,
    @ColumnInfo(name = "objectId") val objectId: Int,
    @ColumnInfo(name = "consumptionDetailId") val consumptionDetailId: Int=0,
    @ColumnInfo(name = "consumptionId") val consumptionId: Int=0,
    @ColumnInfo(name = "articleCode") val articleCode: String="",
    @ColumnInfo(name = "quantity") val quantity: Int=0,
    @ColumnInfo(name = "unitOfMeasure") val unitOfMeasure: String="",
    @ColumnInfo(name = "creationDate") val creationDate: String=""+Dates().date(),
    @ColumnInfo(name = "delivered") val delivered: Int=0,
    @ColumnInfo(name = "notes") val notes: String?=""
)
fun SyncConsumible.toDomain() = SyncConsumible(localId,objectId,consumptionDetailId,consumptionId,articleCode,quantity,unitOfMeasure,creationDate,delivered,notes)
fun SyncConsumible.toDatabase() = SyncConsumible(localId=localId,objectId=objectId,consumptionDetailId=consumptionDetailId,consumptionId=consumptionId,articleCode=articleCode,quantity=quantity,unitOfMeasure=unitOfMeasure,creationDate=creationDate,delivered=delivered,notes=notes)