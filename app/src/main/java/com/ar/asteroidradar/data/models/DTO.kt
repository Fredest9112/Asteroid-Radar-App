package com.ar.asteroidradar.data.models

import com.ar.asteroidradar.data.database.AsteroidDB

fun ArrayList<Asteroid>.asDatabaseModel(): Array<AsteroidDB> {
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
    }.toTypedArray()
}