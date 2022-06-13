package ru.photographer.models

import kotlinx.serialization.Serializable

@Serializable
data class ImageResponseModel(
    val ans: String
)