package com.efe.githubrepolistener.data.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

open class GithubModel (
    @SerializedName("name")
    open var name: String,
    @SerializedName("stargazers_count")
    open var starCount: Int,
    @SerializedName("open_issues_count")
    open var openIssuesCount: Int,
    @SerializedName("owner")
    open var owner: Owner
)

class GithubView (
    val model: GithubModel,
    val isFavorite: Boolean
)

data class Owner(
    @SerializedName("login")
    var login: String,
    @SerializedName("avatar_url")
    var avatarUrl: String
)