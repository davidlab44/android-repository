package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable
@Serializable
data class Dv (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "cod") val cod: Int = 0,
    @ColumnInfo(name = "description") val description: String,
    )
//Lo usa como tipo de dato base el preoperativo y postoperativo
fun Dv.toDomain() = Dv(cod,description)
fun Dv.toDatabase() = Dv(cod=cod,description=description)


