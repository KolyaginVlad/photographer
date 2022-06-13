package ru.photographer.models

@kotlinx.serialization.Serializable
data class ImageReceiveModel(
    val image: ByteArray
)
