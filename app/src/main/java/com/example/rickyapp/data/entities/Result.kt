package com.example.rickyapp.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Room to understand
 */

@Entity(tableName = "RickMartin_Characters")
data class Result(
    val created: String,
    val gender: String,
    @PrimaryKey
    val id: Int,
    val image: String,
    val name: String,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)