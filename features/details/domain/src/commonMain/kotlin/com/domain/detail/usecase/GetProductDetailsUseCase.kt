package com.domain.detail.usecase

import com.domain.detail.repository.DetailsRepository

class GetProductDetailsUseCase(private val detailsRepository: DetailsRepository) {
    suspend fun execute(id: Int) = detailsRepository.getProductDetails(id)
}