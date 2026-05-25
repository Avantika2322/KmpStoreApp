package com.presentation.search.viewmodel

import com.domain.search.model.Product


data class SearchUiState(
    val isLoading: Boolean = false,
    val products: List<Product> = emptyList(),
    val error: String? = null
)
