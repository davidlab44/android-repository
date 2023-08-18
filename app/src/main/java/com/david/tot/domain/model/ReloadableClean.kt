package com.david.tot.domain.model

import kotlinx.serialization.*

@Serializable
data class ReloadableClean (
    val consumptionDetailId: Int = 0,
    var consumptionId: Int = 1,
    val articleCode: String = "",
    val quantity: Int = 1,
    val unitOfMeasure: String = "UND",
    val creationDate: String = "2023-08-08T00:48:12.104Z",
    val delivered: Int = 0
)
fun ReloadableClean.toDomain() = ReloadableClean(consumptionDetailId,consumptionId,articleCode,quantity,unitOfMeasure,creationDate,delivered)
fun ReloadableClean.toDatabase() = ReloadableClean(consumptionDetailId=consumptionDetailId,consumptionId=consumptionId,articleCode=articleCode,quantity=quantity,unitOfMeasure=unitOfMeasure,creationDate=creationDate,delivered=delivered)
