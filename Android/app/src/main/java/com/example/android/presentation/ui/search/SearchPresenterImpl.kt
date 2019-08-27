package com.example.android.presentation.ui.search

import com.example.android.data.remote.GithubApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchPresenterImpl(val apiService: GithubApi): SearchPresenter<UserDataList>() {
    override fun getGithubUserList(q: String) {
        compositeDisposable?.add(apiService.getUser(q, "repositories", "desc")
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({userData?.onDataLoaded(it)}, {userData?.onDataFailed()}))

    }
}