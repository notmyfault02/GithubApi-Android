package com.example.android.presentation.ui.repo

import com.example.android.presentation.UserData
import io.reactivex.disposables.CompositeDisposable

abstract class RepoPresenter<U: UserData> {
    protected var compositeDisposable: CompositeDisposable? = null

    var userData: U? = null
        set(value) {
            if(this.userData !== value) {
                field = value
                compositeDisposable = CompositeDisposable()
            }
        }

    abstract fun getGithubUser(q: String)
}