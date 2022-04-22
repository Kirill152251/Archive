package com.example.di

import com.example.repository.MainScreenRepository
import com.example.repository.MainScreenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindMainScreenRepository(repositoryImpl: MainScreenRepositoryImpl) : MainScreenRepository
}