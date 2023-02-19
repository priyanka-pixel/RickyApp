package com.example.rickyapp.characterlist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.rickyapp.data.entities.Result
import com.example.rickyapp.data.repository.CharacterRepository
import com.example.rickyapp.utils.Resource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterListViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val characterList = listOf(
        Result("10-10-22", "male", 1, "", "John", "Human", "Alive", "", ""),
        Result("05-13-20", "female", 2, "", "Katie", "Alien", "Alive", "", ""),
        Result("07-02-18", "male", 5, "", "Albert", "Alien", "Alive", "", "")
    )

    private val repository: CharacterRepository = mockk(relaxed = true) {
        every { getCharacters() } returns MutableLiveData(Resource.success(characterList))
    }
    private val charactersObserver: Observer<Resource<List<Result>>> = mockk(relaxed = true)
    private lateinit var viewModel: CharacterListViewModel

    @Before
    fun setup() {
        viewModel = CharacterListViewModel(repository)
        viewModel.repository.observeForever(charactersObserver)
    }

    @Test
    fun `get characters should return emit list of characters`() {
        assert(viewModel.repository.value == Resource.success(characterList) )
        verify { charactersObserver.onChanged(Resource.success(characterList)) }
    }
}