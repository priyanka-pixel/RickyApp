package com.example.rickyapp.characterlist

import androidx.lifecycle.ViewModel
import com.example.rickyapp.data.repository.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    repository: CharacterRepository) : ViewModel(){

        val repository = repository.getCharacters()
}