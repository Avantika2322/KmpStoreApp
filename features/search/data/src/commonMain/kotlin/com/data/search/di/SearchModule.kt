package com.data.search.di

import com.data.search.repository.SearchRepoImpl
import com.domain.search.repository.SearchRepository
import org.koin.core.module.Module
import org.koin.dsl.module

fun getSearchDataModule() : Module{
    return module {
        single<SearchRepository> {
            SearchRepoImpl(get())
        }
    }
}