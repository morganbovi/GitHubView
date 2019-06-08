package com.apkrocket.githubview.store.data

import com.apkrocket.githubview.store.model.Repo
import com.apkrocket.githubview.store.rest.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubStore(val githubService: GithubService) {

    suspend fun fetchReposAsync(): Result<List<Repo>> = withContext(Dispatchers.IO) {
        try {
            Result.Success(githubService.fetchReposAsync())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }


}