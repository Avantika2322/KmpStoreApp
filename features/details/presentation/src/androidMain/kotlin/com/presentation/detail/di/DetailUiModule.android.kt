package com.presentation.detail.di

import com.presentation.detail.viewmodel.DetailsViewModel
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual fun getDetailsUiModule(): org.koin.core.module.Module {
    return module {
        viewModel { DetailsViewModel(get()) }
    }
}