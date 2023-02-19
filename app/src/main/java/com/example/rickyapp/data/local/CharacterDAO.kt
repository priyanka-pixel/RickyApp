package com.example.rickyapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickyapp.data.entities.Result


/**
 * Room library to understand: @Dao
 * Insert
 * Delete
 * Get all values
 */

@Dao
interface CharacterDAO {
    // API response will be stored
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun insertAll(result: List<com.example.rickyapp.data.entities.Result>)

    // Display items in recyclerview
    @Query("SELECT * FROM RickMartin_Characters")
    fun getAllCharacters() : LiveData<List<com.example.rickyapp.data.entities.Result>>

    /**
     * Queries for Details
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDetails(character: Result)

    @Query("SELECT * FROM RickMartin_Characters WHERE id= :id")
    fun getCharacter(id: Int) : LiveData<Result>
}