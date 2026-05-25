package com.domain.detail.di

import com.domain.detail.usecase.GetProductDetailsUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDomainModule(): Module {
    return module {
        factory { GetProductDetailsUseCase(get()) }
    }
}