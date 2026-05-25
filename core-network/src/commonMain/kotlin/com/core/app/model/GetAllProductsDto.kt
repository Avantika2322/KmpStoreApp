package com.core.app.model

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Int,
    val image: String,
    val name: String,
    val slug: String
)

@Serializable
data class GetAllProductsDtoItem(
    val category: Category,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val slug: String,
    val title: String
)