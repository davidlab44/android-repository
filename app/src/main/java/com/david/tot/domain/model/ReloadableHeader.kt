package com.david.tot.domain.model

import com.david.tot.util.Dates
import kotlinx.serialization.*

@Serializable
data class ReloadableHeader(
    var restockId : Int    = 0,
    var restockerUser  : String = "someString",
    var vehicle       : String = "Vehicle",
    var status        : String = "PENDING",
    var creationDate  : String = ""+ Dates().date(),
    var modifiedDate  : String = ""+Dates().date(),
    var deliveryConfirmationImageUrl  : String = "someUrl",
    var deliveryConfirmationComments  : String = "someComments",
    var confirmerUser  : String = "confirmerUser",
    var deliveryConfirmationDate  : String = "2023-08-10T01:42:45.655Z",
    var consecutive  : String = "0",
    var sourceId  : String = "0",
    var aproved     : Int    = 0
)
