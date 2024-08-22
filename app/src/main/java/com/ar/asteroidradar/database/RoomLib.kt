package com.ar.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM AsteroidDB")
    fun getAsteroids(): LiveData<List<AsteroidDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: AsteroidDB)

    @Query("SELECT * FROM AsteroidDB WHERE closeApproachDate IN (:date)")
    fun getTodayAsteroids(date: String): LiveData<List<AsteroidDB>>

    @Query("SELECT * FROM AsteroidDB WHERE closeApproachDate BETWEEN :startDate AND :endDate")
    fun getWeekAsteroids(startDate: String, endDate: String): LiveData<List<AsteroidDB>>

    @Query("DELETE FROM AsteroidDB WHERE closeApproachDate BETWEEN :startDate AND :endDate")
    fun deleteOldAsteroids(startDate: String, endDate: String)
}

@Database(entities = [AsteroidDB::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
}
