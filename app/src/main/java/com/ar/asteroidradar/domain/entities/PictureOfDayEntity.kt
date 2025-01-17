package com.ar.asteroidradar.domain.entities

import com.ar.asteroidradar.data.models.PictureOfDayRemote
import com.ar.asteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK
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
        url = url ?: PICTURE_OF_DAY_MOCK.url,
        copyright = copyright?.normalize() ?: PICTURE_OF_DAY_MOCK.copyright,
        date = date ?: PICTURE_OF_DAY_MOCK.date,
        explanation = explanation?.normalize() ?: PICTURE_OF_DAY_MOCK.explanation,
        title = title?.normalize() ?: PICTURE_OF_DAY_MOCK.title
    )
}
