package com.seventhson.marvel.di.module


import com.seventhson.marvel.data.ApiInterface
import com.seventhson.marvel.data.AppDatabase
import com.seventhson.marvel.data.getCharacterDetail.CharacterDetailRepositoryImpl
import com.seventhson.marvel.data.getCharacterDetail.dataSource.CharacterDetailLocalDataSource
import com.seventhson.marvel.data.getCharacterDetail.dataSource.CharacterDetailRemoteDataSource
import com.seventhson.marvel.data.getCharacterList.CharacterListRepositoryImpl
import com.seventhson.marvel.data.getCharacterList.dataSource.CharacterLocalDataSource
import com.seventhson.marvel.data.getCharacterList.dataSource.CharacterRemoteDataSource
import com.seventhson.marvel.domain.repository.CharacterDetailRepository
import com.seventhson.marvel.domain.repository.CharacterListRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRemoteDataSource(apiInterface: ApiInterface): CharacterRemoteDataSource =
        CharacterRemoteDataSource(apiInterface)

    @Provides
    @Singleton
    fun provideCharacterLocalDataSource(database: AppDatabase): CharacterLocalDataSource =
        CharacterLocalDataSource(database)

    @Provides
    fun providesCharacterRepository(
        characterRemoteDataSource: CharacterRemoteDataSource,
        characterLocalDataSource: CharacterLocalDataSource
    ): CharacterListRepository {
        return CharacterListRepositoryImpl(characterRemoteDataSource, characterLocalDataSource)
    }

    @Provides
    @Singleton
    fun provideCharacterDetailRemoteDataSource(apiInterface: ApiInterface): CharacterDetailRemoteDataSource =
        CharacterDetailRemoteDataSource(apiInterface)

    @Provides
    @Singleton
    fun provideCharacterDetailLocalDataSource(database: AppDatabase): CharacterDetailLocalDataSource =
        CharacterDetailLocalDataSource(database)

    @Provides
    fun providesCharacterDetailRepository(
        characterDetailRemoteDataSource: CharacterDetailRemoteDataSource,
        characterDetailLocalDataSource: CharacterDetailLocalDataSource
    ): CharacterDetailRepository {
        return CharacterDetailRepositoryImpl(
            characterDetailRemoteDataSource,
            characterDetailLocalDataSource
        )
    }


}