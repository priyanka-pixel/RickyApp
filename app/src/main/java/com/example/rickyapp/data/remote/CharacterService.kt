package com.example.rickyapp.data.remote

import com.example.rickyapp.data.entities.Result
import com.example.rickyapp.data.entities.RickCharacters
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterService {
    // List of characters
    @GET("character")
    suspend fun getAllCharacters() : Response<RickCharacters>

// append ID to the path of the URL
    @GET("character/{id}")
    suspend fun getCharacterDetails(@Path("id") id: Int): Response<Result>
}