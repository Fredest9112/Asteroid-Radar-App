package com.ar.asteroidradar.domain.exceptions

import com.ar.asteroidradar.data.models.PictureOfDayRemote

sealed class PictureResponse {
    data class PictureSuccess (val pictureOfDayRemote: PictureOfDayRemote): PictureResponse()
    data class PictureError (val exception: Exception): PictureResponse()
}