package com.example.android.data.remote

import com.example.android.model.GithubRepo
import com.example.android.model.RepoSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET("search/repositories")
    fun searchRepository(@Query("q") query: String): Call<RepoSearchResponse>

    @GET("repos/{owner}/{name}")
    fun getRepository(
        @Path("owner") ownerLogin: String,
        @Path("name") repoName: String): Call<GithubRepo>
}