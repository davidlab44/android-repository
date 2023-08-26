package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Spotable (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "localId") val localId: Int = 0,
    @ColumnInfo(name = "id") val id: Int = 0,
    //@ColumnInfo(name = "remote_id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nit") val nit: String,
)
//fun Pre.toDomain() = Pre(local_id,name,isSelected)
//fun Pre.toDatabase() = Pre(local_id=local_id,name=name,isSelected=isSelected)