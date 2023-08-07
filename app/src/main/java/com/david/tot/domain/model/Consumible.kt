package com.david.tot.domain.model

import kotlinx.serialization.*

@Serializable
data class Consumible (val id: Int = 0, val quantity: Int = 0)