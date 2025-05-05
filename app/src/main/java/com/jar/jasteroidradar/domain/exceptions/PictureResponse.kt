package com.jar.jasteroidradar.domain.exceptions

import com.jar.jasteroidradar.data.models.PictureOfDayRemote

sealed class PictureResponse {
    data class PictureSuccess (val pictureOfDayRemote: PictureOfDayRemote): PictureResponse()
    data class PictureError (val exception: Exception): PictureResponse()
}