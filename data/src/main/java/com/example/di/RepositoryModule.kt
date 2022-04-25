package com.example.di

import com.example.repository.AppRepository
import com.example.repository.AppRepositoryImpl
import com.example.repository.ProfileRepository
import com.example.repository.ProfileRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindAppRepository(repositoryImpl: AppRepositoryImpl) : AppRepository

    @Binds
    fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl) : ProfileRepository
}