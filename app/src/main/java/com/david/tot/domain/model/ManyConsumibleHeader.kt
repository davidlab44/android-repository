package com.david.tot.domain.model

import kotlinx.serialization.*

@Serializable
data class ManyConsumibleHeader(
    val consumibleList: List<ConsumibleHeader>
)