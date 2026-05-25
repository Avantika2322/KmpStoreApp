package com.domain.search.usecase

import com.domain.search.repository.SearchRepository

class SearchUseCase(
    private val searchRepository: SearchRepository
) {
    suspend fun search(query: String) = searchRepository.searchProducts(query)
}