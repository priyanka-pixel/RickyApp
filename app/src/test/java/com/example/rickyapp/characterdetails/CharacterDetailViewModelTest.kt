package com.example.rickyapp.characterdetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.rickyapp.data.entities.Result
import com.example.rickyapp.data.repository.CharacterRepository
import com.example.rickyapp.utils.Resource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterDetailViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val character =
        Result("10-10-22", "male", 1, "", "John", "Human", "Alive", "", "")

    private val repository: CharacterRepository = mockk(relaxed = true) {
        every { getCharacterDetailsData(character.id) } returns MutableLiveData(Resource.success(character))
        every { getCharacterDetailsData(100) } returns MutableLiveData(Resource.error("Unable to find character with given id"))
    }
    private val characterObserver: Observer<Resource<Result>> = mockk(relaxed = true)
    private lateinit var viewModel: CharacterDetailViewModel

    @Before
    fun setup() {
        viewModel = CharacterDetailViewModel(repository)
        viewModel.character.observeForever(characterObserver)
    }

    @Test
    fun `start details call with valid id should invoke success emission of single character`() {
        viewModel.startDetailsCall(1)
        verify { characterObserver.onChanged(Resource.success(character)) }
    }

    @Test
    fun `start details call with invalid id should invoke emit error`() {
        viewModel.startDetailsCall(100)
        verify { characterObserver.onChanged(Resource.error("Unable to find character with given id")) }
    }
}