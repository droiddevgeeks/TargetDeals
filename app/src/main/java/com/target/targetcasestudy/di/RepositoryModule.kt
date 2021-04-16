package com.target.targetcasestudy.di

import com.target.targetcasestudy.repository.DealsRepository
import com.target.targetcasestudy.repository.DealsRepositoryImpl
import com.target.targetcasestudy.usecase.DealsUseCase
import com.target.targetcasestudy.usecase.DealsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideDealsUseCase(useCaseImpl: DealsUseCaseImpl): DealsUseCase

    @Binds
    abstract fun provideDealsRepository(repoImpl: DealsRepositoryImpl): DealsRepository
}