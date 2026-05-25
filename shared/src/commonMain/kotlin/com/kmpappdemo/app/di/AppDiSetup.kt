package com.kmpappdemo.app.di

import com.core.app.di.getNetworkModule
import com.data.detail.di.getDetailsDataModule
import com.data.search.di.getSearchDataModule
import com.domain.detail.di.getDetailsDomainModule
import com.domain.search.getSearchDomainModule
import com.presentation.detail.di.getDetailsUiModule
import com.presentation.search.di.getSearchUiModule
import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(
            getNetworkModule(),
            getSearchUiModule(),
            getSearchDataModule(),
            getSearchDomainModule(),
            getDetailsUiModule(),
            getDetailsDataModule(),
            getDetailsDomainModule()
        )
    }
}