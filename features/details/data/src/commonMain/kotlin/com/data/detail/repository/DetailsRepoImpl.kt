package com.data.detail.repository

import com.core.app.service.ProductApiService
import com.domain.detail.model.Category
import com.domain.detail.model.Product
import com.domain.detail.repository.DetailsRepository

class DetailsRepoImpl(
    private val apiService: ProductApiService
) : DetailsRepository {

    override suspend fun getProductDetails(id: Int): Result<Product> {
        return apiService.getProductById(id).map { dto ->
            Product(
                id = dto.id,
                title = dto.title,
                description = dto.description,
                price = dto.price,
                category = Category(
                    id = dto.category.id,
                    name = dto.category.name,
                    slug = dto.category.slug,
                    image = dto.category.image
                ),
                images = dto.images,
                slug = dto.slug
            )
        }
    }
}