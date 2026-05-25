package com.core.app.service

import com.core.app.model.GetAllProductsDtoItem
import com.core.app.model.GetProductByIdDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ProductApiService(private val httpClient: HttpClient) {
    suspend fun getAllProducts(): Result<List<GetAllProductsDtoItem>> {
        return try {
            val res = httpClient.get("api/v1/products").body<List<GetAllProductsDtoItem>>()
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getProductById(id: Int): Result<GetProductByIdDto> {
        return try {
            val res = httpClient.get("api/v1/products/$id").body<GetProductByIdDto>()
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun searchProductsBySlug(q: String): Result<List<GetAllProductsDtoItem>> {
        return try {
            val res = httpClient.get("api/v1/products/slug/$q/related").body<List<GetAllProductsDtoItem>>()
            Result.success(res)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
