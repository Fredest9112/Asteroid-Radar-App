package com.ar.asteroidradar.domain.entities

import com.ar.asteroidradar.data.database.AsteroidDB
import com.ar.asteroidradar.data.models.AsteroidRemote

fun List<AsteroidDB>.asDomainEntity(): List<AsteroidRemote> {
    return map {
        AsteroidRemote(
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