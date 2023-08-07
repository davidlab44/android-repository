package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

import kotlinx.serialization.*
import kotlinx.serialization.json.*

@Serializable
data class InventoryOutput (val id: Int = 0, val quantity: Int = 0)

/*
//@Entity(tableName = "inventoryOutputTable")
@Serializable
class InventoryOutput (
    //@PrimaryKey(autoGenerate = true)
     val id: Int = 0,
     val quantity: Int = 0
    )
//fun InventoryOutput.toDomain() = InventoryOutput(id,quantity)
//fun InventoryOutput.toDatabase() = InventoryOutput(id=id, quantity=quantity)

 */