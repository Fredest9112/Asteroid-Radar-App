package com.ar.asteroidradar.domain.entities

import com.ar.asteroidradar.data.database.AsteroidDB
import com.ar.asteroidradar.data.models.Asteroid

fun List<AsteroidDB>.asDomainEntity(): List<Asteroid> {
    return map {
        Asteroid(
            id = it.id,
            codename = it.codename,
            closeApproachDate = it.closeApproachDate,
            absoluteMagnitude = it.absoluteMagnitude,
            estimatedDiameter = it.estimatedDiameter,
            relativeVelocity = it.relativeVelocity,
            distanceFromEarth = it.distanceFromEarth,
            isPotentiallyHazardous = it.isPotentiallyHazardous
        )
    }
}