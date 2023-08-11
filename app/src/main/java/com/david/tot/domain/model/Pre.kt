package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PreTable")
data class Pre (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    //@ColumnInfo(name = "remote_id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "isSelected") val isSelected: Int = 0
    )
//fun Pre.toDomain() = Pre(local_id,name,isSelected)
//fun Pre.toDatabase() = Pre(local_id=local_id,name=name,isSelected=isSelected)