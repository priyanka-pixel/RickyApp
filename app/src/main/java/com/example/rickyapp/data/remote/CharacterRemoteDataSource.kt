package com.example.rickyapp.data.remote

import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
    private val characterService: CharacterService) : BaseDataSource(){

        suspend fun getCharacters() = getResult { characterService.getAllCharacters() }
        suspend fun getCharacterById(id: Int) = getResult { characterService.getCharacterDetails(id) }
    }
