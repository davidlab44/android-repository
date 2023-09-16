package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.david.tot.util.Dates
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "ConfirmableTable")
data class Confirmable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId") val localId: Int = 0,
    @ColumnInfo(name = "restockID") val restockID: Int = 0,
    @ColumnInfo(name = "restockerUser") val restockerUser: String = "",
    @ColumnInfo(name = "restockerDisplayName") val restockerDisplayName: String = "",
    @ColumnInfo(name = "vehicle") val vehicle: String = "",
    @ColumnInfo(name = "status") val status: String = "",
    @ColumnInfo(name = "creationDate") val creationDate: String = "",
    @ColumnInfo(name = "modifiedDate") val modifiedDate: String = ""+ Dates().date(),
    @ColumnInfo(name = "consecutive") val consecutive: String = ""
)
fun Confirmable.toDomain() = Confirmable(localId,restockID,restockerUser,restockerDisplayName,vehicle,status,creationDate,modifiedDate,consecutive)
fun Confirmable.toDatabase() = Confirmable(
    localId=localId,
    restockID=restockID,
    restockerUser=restockerUser,
    restockerDisplayName=restockerDisplayName,
    vehicle=vehicle,
    status=status,
    creationDate=creationDate,
    modifiedDate=modifiedDate,
    consecutive=consecutive,
)
fun Confirmable.toApi() = Confirmable(
    restockID=restockID,
    restockerUser=restockerUser,
    restockerDisplayName=restockerDisplayName,
    vehicle=vehicle,
    status=status,
    creationDate=creationDate,
    modifiedDate=modifiedDate,
    consecutive=consecutive
)
/*
fun Confirmable.toApi() = Confirmable(
    articleCode=articleCode,
    articleDescription=articleDescription,
    unitOfMeasure=unitOfMeasure,
    quantityAvailable=quantityAvailable,
    quantityToStock=quantityToStock,
    quantityConsumed=quantityConsumed
)
*/
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