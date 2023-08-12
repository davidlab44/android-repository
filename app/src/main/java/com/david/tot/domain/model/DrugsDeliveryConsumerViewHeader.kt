package com.david.tot.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "DrugsDeliveryConsumerViewHeaderTable")
data class DrugsDeliveryConsumerViewHeader (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id") val local_id: Int = 0,
    //@ColumnInfo(name = "remote_id") val id: Int,
    @ColumnInfo(name = "consumer") val consumer: String,
    @ColumnInfo(name = "license") val license: String,
    @ColumnInfo(name = "licenseCategory") val licenseCategory: String,
    @ColumnInfo(name = "licenseExpiration") val licenseExpiration: String,
    @ColumnInfo(name = "vehicle") val vehicle: String,
    @ColumnInfo(name = "soat") val soat: String,
    @ColumnInfo(name = "soatExpiration") val soatExpiration: String,
    @ColumnInfo(name = "crtm") val crtm: String,
    @ColumnInfo(name = "crtmExpiration") val crtmExpiration: String
    )
fun DrugsDeliveryConsumerViewHeader.toDomain() = DrugsDeliveryConsumerViewHeader(local_id,consumer,license,licenseCategory,licenseExpiration,vehicle,soat,soatExpiration,crtm,crtmExpiration)
fun DrugsDeliveryConsumerViewHeader.toDatabase() = DrugsDeliveryConsumerViewHeader(local_id=local_id, consumer=consumer, license=license,licenseCategory=licenseCategory,licenseExpiration=licenseExpiration,vehicle=vehicle,soat=soat,soatExpiration=soatExpiration,crtm=crtm,crtmExpiration=crtmExpiration)