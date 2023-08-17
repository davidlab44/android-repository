package com.david.tot.domain.model

import kotlinx.serialization.*

@Serializable
data class ConsumibleHeader(
    var consumptionId : Int    = 0,
    var consumerUser  : String = "someString",
    var vehicle       : String = "VKG028",
    var status        : String = "PENDING",
    var creationDate  : String = "2023-08-10T01:42:45.655Z",
    var restockId     : Int    = 0
)