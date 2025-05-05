package com.jar.jasteroidradar.domain.entities

import com.jar.jasteroidradar.data.models.PictureOfDayRemote
import com.jar.jasteroidradar.utils.Constants.PICTURE_OF_DAY_MOCK
import com.jar.jasteroidradar.utils.normalize

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
