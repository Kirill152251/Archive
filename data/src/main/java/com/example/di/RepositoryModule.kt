package com.example.di

import com.example.repository.AppRepository
import com.example.repository.AppRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAppRepository(repositoryImpl: AppRepositoryImpl) : AppRepository
}