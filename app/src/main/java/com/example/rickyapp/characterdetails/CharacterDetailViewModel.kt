package com.example.rickyapp.characterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.rickyapp.data.entities.Result
import com.example.rickyapp.data.repository.CharacterRepository
import com.example.rickyapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val repository: CharacterRepository) : ViewModel(){

    /**
     * pass the id to backend
     * response from backend
     */

    private val _id = MutableLiveData<Int>()

    private val _character = _id.switchMap { id ->
        repository.getCharacterDetailsData(id)
    }

    val character : LiveData<Resource<Result>> = _character

    /**
     * fun to be used by fragment to pass the id to viewmodel
     */

    fun startDetailsCall(id: Int){
        _id.value = id
    }
}