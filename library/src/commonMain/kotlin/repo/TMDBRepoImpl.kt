package io.github.kotlin.fibonacci.repo

import io.github.kotlin.fibonacci.models.Movie
import io.github.kotlin.fibonacci.models.MoviesResponseContainer
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow

class TMDBRepoImpl(
//    private val movieDao: MovieDao,
) : TMDBRepo {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(kotlinx.serialization.json.Json {
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    override suspend fun getPopularMovies(): MoviesResponseContainer {
//        return moviesService.getPopularMovies()
        return client.get("https://api.themoviedb.org/3/movie/now_playing?api_key=38a73d59546aa378980a88b645f487fc&language=en-US&page=1").body()
    }

    override suspend fun saveFavourite(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun removeFavourite(movie: Movie) {
        TODO("Not yet implemented")
    }

    override suspend fun isFavourite(movie: Movie): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getFavorites(): Flow<List<Movie>> {
        TODO("Not yet implemented")
    }
    /*
        override suspend fun saveFavourite(movie: Movie) {
            movieDao.insert(movie)
        }

        override suspend fun removeFavourite(movie: Movie) {
            movieDao.delete(movie)
        }

        override suspend fun isFavourite(movie: Movie) : Boolean{
            return movieDao.getMovieById(movie.id) != null
        }

        override suspend fun getFavorites(): Flow<List<Movie>> {
            return movieDao.getAll()
        }*/
}