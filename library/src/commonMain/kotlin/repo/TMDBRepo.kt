package io.github.kotlin.fibonacci.repo

import io.github.kotlin.fibonacci.models.Movie
import io.github.kotlin.fibonacci.models.MoviesResponseContainer
import kotlinx.coroutines.flow.Flow

interface TMDBRepo {

    suspend fun getPopularMovies(): MoviesResponseContainer

    suspend fun saveFavourite(movie: Movie)

    suspend fun removeFavourite(movie: Movie)

    suspend fun isFavourite(movie: Movie): Boolean

    suspend fun getFavorites(): Flow<List<Movie>>
}