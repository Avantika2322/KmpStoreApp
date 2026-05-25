package com.domain.search.model

data class Product(
    val category: Category,
    val description: String,
    val id: Int,
    val images: List<String>,
    val price: Int,
    val slug: String,
    val title: String
)

data class Category(
    val id: Int,
    val image: String,
    val name: String,
    val slug: String
)