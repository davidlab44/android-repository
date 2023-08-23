package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.david.tot.util.Dates
import kotlinx.serialization.Serializable


//BORRAR NO SE NECESITA USAR A menos que no pueda extaraer los parametros de json object en el API
@Serializable
data class ConfirmableClean (
    @ColumnInfo(name = "P_RestockID") val P_RestockID: Int = 0,
    @ColumnInfo(name = "P_User") val P_User: String = "",
    @ColumnInfo(name = "P_Vehicle") val P_Vehicle: String = "",
    @ColumnInfo(name = "p_DeliveryConfirmationImageUrl") val p_DeliveryConfirmationImageUrl: String = "",
    @ColumnInfo(name = "P_DeliveryConfirmationComments") val P_DeliveryConfirmationComments: String = ""
    /*
    @ColumnInfo(name = "restockID") val restockID: Int = 0,
    @ColumnInfo(name = "restockerUser") val restockerUser: String = "",
    @ColumnInfo(name = "restockerDisplayName") val restockerDisplayName: String = "",
    @ColumnInfo(name = "vehicle") val vehicle: String = "",
    @ColumnInfo(name = "status") val status: String = "",
    @ColumnInfo(name = "creationDate") val creationDate: String = "",
    @ColumnInfo(name = "modifiedDate") val modifiedDate: String = ""+ Dates().date(),
    @ColumnInfo(name = "consecutive") val consecutive: String = ""
*/
)
/*
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
*/