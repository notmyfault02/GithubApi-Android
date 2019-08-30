package com.example.android.presentation.di

import com.example.android.presentation.ui.repo.RepoPresenter
import com.example.android.presentation.ui.repo.RepoPresenterImpl
import org.koin.core.module.Module
import org.koin.dsl.module

val userListModule: Module = module {
    factory {
        RepoPresenterImpl(get()) as RepoPresenter<*>
    }

}