package com.ar.asteroidradar.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ar.asteroidradar.data.models.Asteroid

@Entity
data class AsteroidDB(
    @PrimaryKey
    val id: Long,
    val codename: String,
    val closeApproachDate: String,
    val absoluteMagnitude: Double,
    val estimatedDiameter: Double,
    val relativeVelocity: Double,
    val distanceFromEarth: Double,
    val isPotentiallyHazardous: Boolean
)
