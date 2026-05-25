package com.presentation.detail.di

import com.presentation.detail.viewmodel.DetailsViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.dsl.module

private val viewModelModule = module {
    factory { DetailsViewModel(get()) }
}

actual fun getDetailsUiModule(): org.koin.core.module.Module {
    return viewModelModule
}

class DetailsViewModelProvider : KoinComponent {

    fun provideDetailsViewModel(): DetailsViewModel = get()

}