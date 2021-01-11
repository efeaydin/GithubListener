package com.efe.githubrepolistener.data.remote.repository

import android.util.Log
import com.efe.githubrepolistener.BuildConfig
import com.efe.githubrepolistener.data.model.GithubModel
import com.efe.githubrepolistener.data.model.Owner
import com.efe.githubrepolistener.data.remote.service.GithubService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface GithubRepository {
    companion object {
        val default: GithubRepository by lazy {
            if (BuildConfig.MOCK_REPOSITORY) {
                MockGithubRepository()
            } else {
                DefaultGithubRepository()
            }
        }
    }

    suspend fun getRepos(
        user: String,
        completion: suspend (List<GithubModel>) -> Unit,
        error: suspend () -> Unit
    )

}

class DefaultGithubRepository(val githubService: GithubService = GithubService.default) :
    GithubRepository {

    override suspend fun getRepos(
        user: String,
        completion: suspend (List<GithubModel>) -> Unit,
        error: suspend () -> Unit
    ) = withContext(Dispatchers.IO) {
        try {
            val result = githubService.getRepos(user)
            completion(result)
        } catch (throwable: Throwable) {
            error()
        }
    }

}

class MockGithubRepository : GithubRepository {
    override suspend fun getRepos(
        user: String,
        completion: suspend (List<GithubModel>) -> Unit,
        error: suspend () -> Unit
    ) {
        val list = mutableListOf<GithubModel>()
        list.add(
            GithubModel(
                12L,
                "Repo 1",
                5,
                3,
                Owner(
                    "efeaydin",
                    "https://avatars0.githubusercontent.com/u/15197931?v=4"
                )
            )
        )
        completion(
            list
        )
    }

}