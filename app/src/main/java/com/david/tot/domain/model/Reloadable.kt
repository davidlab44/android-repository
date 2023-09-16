package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "ReloadableTable")
data class Reloadable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "restockID") val restockID: Int = 0,
    @ColumnInfo(name = "restockerUser") val restockerUser: String = "",
    @ColumnInfo(name = "restockerDisplayName") val restockerDisplayName: String = "",
    @ColumnInfo(name = "vehicle") val vehicle: String = "",
    @ColumnInfo(name = "status") var status: String = "",
    @ColumnInfo(name = "creationDate") val creationDate: String = "",
    @ColumnInfo(name = "modifiedDate") var modifiedDate: String = "",
    @ColumnInfo(name = "consecutive") var consecutive: String = "",
)
fun Reloadable.toDomain() = Reloadable(restockID=restockID,restockerUser=restockerUser,restockerDisplayName=restockerDisplayName,
    vehicle=vehicle,status=status,creationDate=creationDate,modifiedDate=modifiedDate,consecutive=consecutive)
fun Reloadable.toDatabase() = Reloadable(restockID=restockID,restockerUser=restockerUser,restockerDisplayName=restockerDisplayName,
    vehicle=vehicle,status=status,creationDate=creationDate,modifiedDate=modifiedDate,consecutive=consecutive)
fun Reloadable.toApi() = Reloadable(restockID=restockID,restockerUser=restockerUser,restockerDisplayName=restockerDisplayName,
    vehicle=vehicle,status=status,creationDate=creationDate,modifiedDate=modifiedDate,consecutive=consecutive)