package com.domain.detail.repository

import com.domain.detail.model.Product

interface DetailsRepository {
    suspend fun getProductDetails(id: Int): Result<Product>
}