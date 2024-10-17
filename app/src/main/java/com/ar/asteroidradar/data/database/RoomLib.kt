package com.ar.asteroidradar.data.database

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import kotlinx.coroutines.flow.Flow

@Dao
interface AsteroidDao {
    @Query("SELECT * FROM AsteroidDB")
    fun getAsteroids(): Flow<List<AsteroidDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: AsteroidDB)

    @Query("SELECT * FROM AsteroidDB WHERE closeApproachDate IN (:date)")
    fun getTodayAsteroids(date: String): Flow<List<AsteroidDB>>

    @Query("SELECT * FROM AsteroidDB WHERE closeApproachDate BETWEEN :startDate AND :endDate")
    fun getWeekAsteroids(startDate: String, endDate: String): Flow<List<AsteroidDB>>

    @Query("DELETE FROM AsteroidDB WHERE closeApproachDate BETWEEN :startDate AND :endDate")
    fun deleteOldAsteroids(startDate: String, endDate: String)
}

@Database(entities = [AsteroidDB::class], version = 1, exportSchema = false)
abstract class AsteroidDatabase : RoomDatabase() {
    abstract val asteroidDao: AsteroidDao
}
