package com.presentation.detail.viewmodel

import com.domain.detail.usecase.GetProductDetailsUseCase
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DetailsViewModel(
    private val detailsUseCase: GetProductDetailsUseCase
) : ViewModel() {


    private val _uiState = MutableStateFlow(DetailsUiState())

    val uiState: StateFlow<DetailsUiState> =
        _uiState.asStateFlow()

    // For iOS
    val uiStateCommon = uiState.asCommonFlow()


    fun getProductDetail(id: Int) = viewModelScope.launch {
        _uiState.update { DetailsUiState(isLoading = true) }
        detailsUseCase.execute(id).onSuccess { data ->
            _uiState.update { DetailsUiState(productDetails = data) }
        }.onFailure { error ->
            _uiState.update { DetailsUiState(error = error.message.toString()) }
        }
    }
}