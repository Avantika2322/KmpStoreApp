package com.domain.search.repository

import com.domain.search.model.Product

interface SearchRepository {
    suspend fun searchProducts(query: String): Result<List<Product>>
}