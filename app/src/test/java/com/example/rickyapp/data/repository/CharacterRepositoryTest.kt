package com.example.rickyapp.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.rickyapp.data.entities.Result
import com.example.rickyapp.data.local.CharacterDAO
import com.example.rickyapp.data.remote.CharacterRemoteDataSource
import com.example.rickyapp.utils.Resource
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CharacterRepositoryTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val charactersObserver: Observer<Resource<List<Result>>> = mockk(relaxed = true)
    private val characterObserver: Observer<Resource<Result>> = mockk(relaxed = true)
    private val remoteDataSource: CharacterRemoteDataSource = mockk(relaxed = true)
    private val localDataSource: CharacterDAO = mockk(relaxed = true)
    private lateinit var repository: CharacterRepository

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        repository = CharacterRepository(remoteDataSource, localDataSource)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `get characters should return live data of character data`() {
        repository.getCharacters().observeForever(charactersObserver)
        verify { charactersObserver.onChanged(any()) }
    }

    @Test
    fun `get character detail data should return live data of single character data`() {
        repository.getCharacterDetailsData(1).observeForever(characterObserver)
        verify { characterObserver.onChanged(any()) }
    }
}