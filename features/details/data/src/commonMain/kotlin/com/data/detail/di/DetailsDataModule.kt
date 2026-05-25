package com.data.detail.di

import com.data.detail.repository.DetailsRepoImpl
import com.domain.detail.repository.DetailsRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDetailsDataModule(): Module {
    return module {
        single<DetailsRepository> { DetailsRepoImpl(get()) }
    }
}