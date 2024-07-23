package com.example.movieapp.data.repositories

import com.example.movieapp.data.repositories.interfaces.IMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun providesRepository(repository: MoviesRepository): IMoviesRepository
}
