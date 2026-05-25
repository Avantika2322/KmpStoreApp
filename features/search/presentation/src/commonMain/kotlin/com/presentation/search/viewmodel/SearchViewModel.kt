package com.presentation.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.domain.search.usecase.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchUseCase: SearchUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()

    private val _query = MutableStateFlow("classic-blue-baseball-cap")
    val query: StateFlow<String> = _query.asStateFlow()

    // 👇 expose for iOS
    val uiStateCommon = uiState.asCommonFlow()
    val queryCommon = query.asCommonFlow()

    fun updateQuery(query: String) {
        _query.update { query }
    }

    init {
        viewModelScope.launch {
            _query.filter { it.isNotEmpty() }
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest {
                    _uiState.update { it.copy(isLoading = true) }
                    searchUseCase.search(_query.value)
                        .onSuccess { result ->
                            _uiState.update { state ->
                                state.copy(
                                    products = result,
                                    isLoading = false,
                                    error = null
                                )
                            }
                        }
                        .onFailure { throwable ->
                            _uiState.update { state ->
                                state.copy(
                                    error = throwable.message,
                                    isLoading = false
                                )
                            }
                        }
                }
        }
    }

}