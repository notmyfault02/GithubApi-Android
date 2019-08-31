package com.example.android.data.remote

import com.example.android.presentation.model.RepoSearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET("/search/users")
    fun getUser(
        @Query("q") query: String,
        @Query("sort") sort: String,
        @Query("order") order: String
    ): Single<RepoSearchResponse>
}