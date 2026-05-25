package com.presentation.search.di

import com.presentation.search.viewmodel.SearchViewModel
import org.koin.dsl.module
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

private val viewModelModule = module{
    factory { SearchViewModel(get()) }
}

actual fun getSearchUiModule(): org.koin.core.module.Module {
   return viewModelModule
}

class SearchViewModelProvider : KoinComponent {
    fun provideSearchViewModel(): SearchViewModel = get()
}