package com.ar.asteroidradar.domain.entities

import com.ar.asteroidradar.data.models.PictureOfDayRemote
import com.ar.asteroidradar.utils.normalize

data class PictureOfDay(
    val url: String,
    val copyright: String,
    val date: String,
    val explanation: String,
    val title: String
)

fun PictureOfDayRemote.asDomainEntity(): PictureOfDay {
    return PictureOfDay(
        url = url,
        copyright = copyright.normalize(),
        date = date,
        explanation = explanation.normalize(),
        title = title.normalize()
    )
}