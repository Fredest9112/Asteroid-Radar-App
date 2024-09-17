package com.ar.asteroidradar.data.models

import com.squareup.moshi.Json

data class PictureOfDayRemote(
    val copyright: String,
    val date: String,
    val explanation: String,
    val hdurl: String,
    @Json(name = "media_type") val mediaType: String,
    val title: String,
    val url: String
)
