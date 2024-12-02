package io.github.kotlin.fibonacci.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviesResponseContainer(
    @SerialName("page")
    val page: Int,
    @SerialName("results")
    val results: List<Movie>,
)