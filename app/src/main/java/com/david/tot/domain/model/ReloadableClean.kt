package com.david.tot.domain.model

import kotlinx.serialization.*

@Serializable
data class ReloadableClean (
    val restockDetailId: Int = 0,
    var restockId: Int = 0,
    val articleCode: String = "",
    val quantity: Double = 0.0,
    val unitOfMeasure: String = "UND",
    val creationDate: String = "2023-08-08T00:48:12.104Z",
    val delivered: Int = 0
)
fun ReloadableClean.toDomain() = ReloadableClean(restockDetailId,restockId,articleCode,quantity,unitOfMeasure,creationDate,delivered)
fun ReloadableClean.toDatabase() = ReloadableClean(restockDetailId=restockDetailId,restockId=restockId,articleCode=articleCode,quantity=quantity,unitOfMeasure=unitOfMeasure,creationDate=creationDate,delivered=delivered)
