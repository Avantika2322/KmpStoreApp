package com.core.app.model

import kotlinx.serialization.Serializable

@Serializable
data class GetProductByIdDto(
    val category: Category,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val slug: String,
    val title: String
)