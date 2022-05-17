package com.udacity.asteroidradar.api

import com.udacity.asteroidradar.data.Asteroid
import com.udacity.asteroidradar.database.AsteroidDB

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