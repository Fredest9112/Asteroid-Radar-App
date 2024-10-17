package com.ar.asteroidradar.domain.entities

import com.ar.asteroidradar.data.database.AsteroidDB
import com.ar.asteroidradar.data.models.AsteroidRemote

fun List<AsteroidRemote>.asDomainEntity(): List<AsteroidDB> {
    return map {
        AsteroidDB(
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