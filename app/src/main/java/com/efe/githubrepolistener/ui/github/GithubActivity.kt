package com.efe.githubrepolistener.ui.github

import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.efe.githubrepolistener.R
import com.efe.githubrepolistener.ui.base.BaseActivity
import com.efe.githubrepolistener.ui.github.fragment.GithubDetailsFragment
import com.efe.githubrepolistener.ui.github.fragment.GithubListFragment
import com.efe.githubrepolistener.utils.extensions.hideKeyboard

class GithubActivity : BaseActivity(), GithubViewModelNavigator {

    private lateinit var viewModel: GithubViewModel

    override fun getViewModel(): ViewModel {
        viewModel = ViewModelProvider(this).get(GithubViewModel::class.java)
        viewModel.navigator = this
        return viewModel
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.github_activity)

        viewModel.navigator?.navigateHome()

    }

    override fun navigateHome() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container_view,
                GithubListFragment(), GithubListFragment.TAG)
            //.addToBackStack(null)
            .commit()
    }

    override fun navigateDetails() {

        hideKeyboard()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container_view,
                GithubDetailsFragment(), GithubDetailsFragment.TAG)
            .addToBackStack(null)
            .commit()
    }

    override fun backPressed() {
        onBackPressed()
    }


}