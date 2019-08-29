package com.example.android.presentation.ui.repo

import com.example.android.data.remote.GithubApi
import com.example.android.presentation.ui.search.UserDataList
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RepoPresenterImpl(val apiService: GithubApi): RepoPresenter<UserDataList>() {
    override fun getGithubUser(q: String) {
        compositeDisposable?.add(apiService.getUser(q, "repository", "desc")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userData?.onDataLoaded(it) }, {userData?.onDataFailed()}))
    }
}