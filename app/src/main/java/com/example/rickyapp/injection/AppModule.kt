package com.example.rickyapp.injection

import android.content.Context
import com.example.rickyapp.data.local.AppDatabase
import com.example.rickyapp.data.local.CharacterDAO
import com.example.rickyapp.data.remote.CharacterRemoteDataSource
import com.example.rickyapp.data.remote.CharacterService
import com.example.rickyapp.data.repository.CharacterRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    /**
     * 1 Retrofit
     * 2 APIService
     * 3 Remote DataSource
     * 4 Local Datasource
     * 5 Repository -> viewmodel
     *
      */


    /**
     * 1 Retrofit
     */
    @Provides
    fun provideGSON(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson) : Retrofit = Retrofit.Builder()
        .baseUrl("https://rickandmortyapi.com/api/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    //==============================================
    /**
     * 2 APIService
     */
    @Provides
    fun provideCharacterService(retrofit: Retrofit) : CharacterService
    = retrofit.create(CharacterService::class.java)

    //===============================================

    /**
     * 3 Remote DataSource
     */
    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(characterService: CharacterService)
    = CharacterRemoteDataSource(characterService)

    //=================================

    /**
     * 4 Local DataSource
     */

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context)
    = AppDatabase.getDatabase(appContext)

    @Singleton
    @Provides
    fun provideCharacterDAO(appDatabase: AppDatabase)
    = appDatabase.characterDAO()

    //=====================================

    /**
     * 5 Repository
     */

    @Singleton
    @Provides
    fun provideRepository(remoteDataSource: CharacterRemoteDataSource, localDataSource: CharacterDAO)
    = CharacterRepository(remoteDataSource, localDataSource)
}