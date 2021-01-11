package com.efe.githubrepolistener.ui.github

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.efe.githubrepolistener.data.model.GithubView
import com.efe.githubrepolistener.data.remote.repository.GithubRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface GithubViewModelNavigator {
    fun navigateHome()
    fun navigateDetails()
    fun backPressed()
}

class GithubViewModel : ViewModel() {

    var navigator: GithubViewModelNavigator? = null

    private val githubRepository: GithubRepository = GithubRepository.default

    val githubRepos: MutableLiveData<List<GithubView>> = MutableLiveData()
    val searchText: MutableLiveData<String> = MutableLiveData()

    val githubRepo: MutableLiveData<GithubView> = MutableLiveData()

    fun callGithubRepos() {

        searchText.value?.let { searchText ->
            //contentLoading.value = true
            viewModelScope.launch(Dispatchers.Default) {
                githubRepository.getRepos(searchText, { githubModels ->

                    val githubViewList = mutableListOf<GithubView>()

                    for (githubModel in githubModels) {
                        githubViewList.add(
                            GithubView(
                                githubModel,
                                false
                            )
                        )
                    }

                    withContext(Dispatchers.Main) {
                        //contentLoading.value = false
                        githubRepos.value = githubViewList
                    }
                }, {
                    withContext(Dispatchers.Main) {
                        //contentLoading.value = false
                        /*errorHandler?.handleError(
                            errorDTO?.errorMessageForUser
                                ?: getApplication<Application>().getString(
                                    R.string.general_error_message
                                )
                        )*/
                        val githubViewList = mutableListOf<GithubView>()
                        withContext(Dispatchers.Main) {
                            //contentLoading.value = false
                            githubRepos.value = githubViewList
                        }
                    }
                })
            }
        }
    }

}