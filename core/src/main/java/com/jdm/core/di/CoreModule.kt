package com.jdm.core.di

import com.jdm.core.repository.KeyRepository
import com.jdm.core.repository.KeyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {
    @Binds
    fun bindsKeyRepository(
        keyRepositoryImpl: KeyRepositoryImpl
    ): KeyRepository
}