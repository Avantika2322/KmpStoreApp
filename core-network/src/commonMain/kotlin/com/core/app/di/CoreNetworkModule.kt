package com.core.app.di

import com.core.app.client.HttpClientFactory
import com.core.app.service.ProductApiService
import org.koin.core.module.Module
import org.koin.dsl.module

fun getNetworkModule(): Module{
    return module {
        single {
            HttpClientFactory.getInstance()
        }
        single {
            ProductApiService(get())
        }
    }
}