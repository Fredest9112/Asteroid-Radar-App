package com.ar.asteroidradar.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AsteroidRemote(
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
) : Parcelable