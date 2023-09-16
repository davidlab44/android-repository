package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "SpotableTable")
data class Spotable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId") val localId: Int = 0,
    @ColumnInfo(name = "id") val id: Int = 0,
    //@ColumnInfo(name = "remote_id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nit") val nit: String,
)
fun Spotable.toDomain() = Spotable(localId,id,name,nit)
fun Spotable.toDatabase() = Spotable(localId=localId,id=id,name=name,nit=nit)