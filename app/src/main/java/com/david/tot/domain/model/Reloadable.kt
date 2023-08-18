package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "ReloadableTable")
data class Reloadable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId") val localId: Int = 0,
    @ColumnInfo(name = "articleCode") val articleCode: String = "",
    @ColumnInfo(name = "articleDescription") val articleDescription: String = "",
    @ColumnInfo(name = "unitOfMeasure") val unitOfMeasure: String = "",
    @ColumnInfo(name = "quantityAvailable") var quantityAvailable: Double = 0.0,
    @ColumnInfo(name = "quantityToStock") val quantityToStock: Double = 0.0,
    @ColumnInfo(name = "quantityConsumed") var quantityConsumed: Double = 0.0
)
fun Reloadable.toDomain() = Reloadable(localId,articleCode,articleDescription,unitOfMeasure,quantityAvailable,quantityToStock,quantityConsumed)
fun Reloadable.toDatabase() = Reloadable(
    localId=localId,
    articleCode=articleCode,
    articleDescription=articleDescription,
    unitOfMeasure=unitOfMeasure,
    quantityAvailable=quantityAvailable,
    quantityToStock=quantityToStock,
    quantityConsumed=quantityConsumed,
)
fun Reloadable.toApi() = Reloadable(
    articleCode=articleCode,
    articleDescription=articleDescription,
    unitOfMeasure=unitOfMeasure,
    quantityAvailable=quantityAvailable,
    quantityToStock=quantityToStock,
    quantityConsumed=quantityConsumed
)
/*
data class Reloadable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId") val localId: Int = 0,
    //@ColumnInfo(name = "remote_id") val id: Int,
    @ColumnInfo(name = "restockId") val restockId: String,
    @ColumnInfo(name = "restockerUser") val restockerUser: String,
    @ColumnInfo(name = "restockerDisplayName") val restockerDisplayName: String,
    @ColumnInfo(name = "vehicle") var vehicle: String,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "creationDate") val creationDate: String,
    @ColumnInfo(name = "modifiedDate") val modifiedDate: String,
    @ColumnInfo(name = "consecutive") val consecutive: Int = 0,
    @ColumnInfo(name = "consumedQuantity") var consumedQuantity: Int = 0
    )
fun Reloadable.toDomain() = Reloadable(localId,restockId,restockerUser,restockerDisplayName,vehicle,status,creationDate,modifiedDate,consecutive,consumedQuantity)
fun Reloadable.toDatabase() = Reloadable(
    localId=localId,
    restockId=restockId,
    restockerUser=restockerUser,
    restockerDisplayName=restockerDisplayName,
    vehicle=vehicle,
    status=status,
    creationDate=creationDate,
    modifiedDate=modifiedDate,
    consecutive=consecutive,
    consumedQuantity=consumedQuantity,
)
*/