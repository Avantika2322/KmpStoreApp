package com.presentation.search.di
import com.presentation.search.viewmodel.SearchViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual fun getSearchUiModule(): Module {
   return module {
        viewModel { SearchViewModel(get()) }
    }
}