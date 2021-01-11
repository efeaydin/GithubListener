package com.efe.githubrepolistener.ui.github.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.efe.githubrepolistener.R
import com.efe.githubrepolistener.databinding.GithubDetailsFragmentBinding
import com.efe.githubrepolistener.ui.base.BaseFragment
import com.efe.githubrepolistener.ui.github.GithubViewModel

class GithubDetailsFragment: BaseFragment() {

    lateinit var binding: GithubDetailsFragmentBinding

    lateinit var viewModel: GithubViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<GithubDetailsFragmentBinding>(inflater, R.layout.github_details_fragment, container, false).apply {
            lifecycleOwner = this@GithubDetailsFragment
            viewModel = this@GithubDetailsFragment.viewModel
        }

        return binding.root
    }

    override fun getViewModel(): ViewModel {
        viewModel = activity?.let {
            ViewModelProvider(it).get(GithubViewModel::class.java)
        }?: throw Exception("Invalid Activity")

        return viewModel
    }

    companion object {
        const val TAG = "GithubDetailsFragment"
    }

}