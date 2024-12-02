package io.github.kotlin.fibonacci.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Movie(
    @SerialName("id")
    val id: String,
    @SerialName("original_title")
    val originalName: String,
    @SerialName("overview")
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("title")
    val title: String,
    @SerialName("original_language")
    val language: String,
)