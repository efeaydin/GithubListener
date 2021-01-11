package com.efe.githubrepolistener.utils.extensions

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

fun Application.getSharedPreferences(): SharedPreferences? {
    return getSharedPreferences("GithubRepoFavorite", Context.MODE_PRIVATE)
}