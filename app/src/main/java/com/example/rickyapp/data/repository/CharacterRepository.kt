package com.example.rickyapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import com.example.rickyapp.data.entities.Result
import com.example.rickyapp.data.local.CharacterDAO
import com.example.rickyapp.data.remote.CharacterRemoteDataSource
import com.example.rickyapp.utils.Resource
import com.example.rickyapp.utils.performGetOperation
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

/**
 * centralized location: data source which will be given to UI
 * database strategy
 */
class CharacterRepository @Inject constructor(
    private val remoteDataSource: CharacterRemoteDataSource,
    private val localDataSource: CharacterDAO
){

    fun getCharacters() =  performGetOperation(
        databaseQuery = {localDataSource.getAllCharacters()},
        networkCall = {remoteDataSource.getCharacters()},
        saveCallResult = {localDataSource.insertAll(it.results)}
    )

    /**
     * Details
     */
    fun getCharacterDetailsData(id: Int) = performGetOperation(
        databaseQuery = {localDataSource.getCharacter(id)},
        networkCall =  {remoteDataSource.getCharacterById(id)},
        saveCallResult = {localDataSource.insertDetails(it)}
    )
}