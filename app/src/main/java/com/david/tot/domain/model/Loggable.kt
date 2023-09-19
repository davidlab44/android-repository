package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
//@Entity(tableName = "AuthenticableTable")
data class Loggable (
    @ColumnInfo(name = "codigo") val codigo: String="",
    @ColumnInfo(name = "nombre") val nombre: String="",
    @ColumnInfo(name = "usuario") val usuario: String="",
    @ColumnInfo(name = "aprobar") val aprobar: Int=0,
    )
fun Loggable.toDomain() = Loggable(codigo=codigo,nombre=nombre,usuario=usuario,aprobar=aprobar)
fun Loggable.toDatabase() = Loggable(codigo=codigo,nombre=nombre,usuario=usuario,aprobar=aprobar)