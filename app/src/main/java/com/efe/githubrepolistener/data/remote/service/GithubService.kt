package com.efe.githubrepolistener.data.remote.service

import com.efe.githubrepolistener.BuildConfig
import com.efe.githubrepolistener.data.factory.RetrofitFactory
import com.efe.githubrepolistener.data.model.GithubModel
import com.efe.githubrepolistener.data.model.Owner
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    companion object {
        val default: GithubService by lazy {
            if (BuildConfig.MOCK_SERVICE) {
                MockGithubService()
            } else {
                RetrofitFactory.defaultRetrofit.create(GithubService::class.java)
            }
        }
    }

    @GET("/users/{user}/repos")
    suspend fun getRepos(@Path("user") user: String): List<GithubModel>

}

class MockGithubService : GithubService {

    override suspend fun getRepos(user: String): List<GithubModel> {
        val list = mutableListOf<GithubModel>()
        list.add(GithubModel(
            12L,
            "Repo 1",
            5,
            3,
            Owner(
                "efeaydin",
                "https://avatars0.githubusercontent.com/u/15197931?v=4"
            )
        ))
        return list
    }

}