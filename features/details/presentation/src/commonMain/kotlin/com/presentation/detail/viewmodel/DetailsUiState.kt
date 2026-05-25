package com.presentation.detail.viewmodel

import com.domain.detail.model.Product

data class DetailsUiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val productDetails: Product? = null
)