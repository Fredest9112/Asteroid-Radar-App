package com.ar.asteroidradar.data.models

import com.squareup.moshi.Json

data class PictureOfDayRemote(
    val copyright: String,
    val date: String,
    val explanation: String,
    @Json(name = "media_type") val mediaType: String,
    @Json(name = "service_version") val serviceVersion: String,
    val title: String,
    val url: String
)
