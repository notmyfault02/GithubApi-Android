package com.example.android.presentation.ui.search

import com.example.android.presentation.UserData
import com.example.android.presentation.model.RepoSearchResponse

interface UserDataList: UserData{
    fun onDataLoaded(storeResponse: RepoSearchResponse)
    fun onDataFailed()

    fun searchGithubUser(searchWord: String)
}