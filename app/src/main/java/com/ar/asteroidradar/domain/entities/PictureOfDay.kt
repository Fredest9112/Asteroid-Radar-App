package com.ar.asteroidradar.domain.entities

import com.ar.asteroidradar.data.models.PictureOfDayRemote

data class PictureOfDay(
    val url: String,
    val copyright: String,
    val date: String,
    val explanation: String
)

fun PictureOfDayRemote.asDomainEntity(): PictureOfDay {
    return PictureOfDay(
        url = url,
        copyright = copyright,
        date = date,
        explanation = explanation
    )
}