package com.example.android.presentation.ui.search

import io.reactivex.disposables.CompositeDisposable

abstract class SearchPresenter<U: com.example.android.presentation.UserData> {

    protected var compositeDisposable: CompositeDisposable? = null

    var userData: U? = null
        set(value) {
            if(this.userData !== value) {
                field = value
                compositeDisposable = CompositeDisposable()
            }
        }

    abstract fun getGithubUserList(q: String)
}