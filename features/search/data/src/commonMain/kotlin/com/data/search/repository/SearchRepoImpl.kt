package com.data.search.repository

import com.core.app.service.ProductApiService
import com.domain.search.model.Category
import com.domain.search.model.Product
import com.domain.search.repository.SearchRepository

class SearchRepoImpl(
    private val apiService: ProductApiService
) : SearchRepository {
    override suspend fun searchProducts(query: String): Result<List<Product>> {
        return apiService.searchProductsBySlug(query).map { list ->
            list.map {dto->
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
}
