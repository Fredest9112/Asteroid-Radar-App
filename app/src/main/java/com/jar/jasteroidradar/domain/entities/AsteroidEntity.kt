package com.jar.jasteroidradar.domain.entities

import com.jar.jasteroidradar.data.database.AsteroidDB
import com.jar.jasteroidradar.data.models.AsteroidRemote

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