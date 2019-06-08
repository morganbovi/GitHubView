package com.apkrocket.githubview.store.rest

import com.apkrocket.githubview.store.model.Repo
import retrofit2.Response
import retrofit2.http.GET

interface GithubService {

    @GET("/orgs/octokit/repos")
    suspend fun fetchReposAsync(): List<Repo>

}