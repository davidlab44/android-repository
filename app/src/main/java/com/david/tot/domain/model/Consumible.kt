package com.david.tot.domain.model

import kotlinx.serialization.*

@Serializable
data class Consumible (
    val consumptionDetailId: Int = 0,
    val consumptionId: Int = 1,
    val articleCode: String = "",
    val quantity: Int = 1,
    val unitOfMeasure: String = "UND",
    val creationDate: String = "2023-08-08T00:48:12.104Z",
    val delivered: Int = 0
)