package com.efe.githubrepolistener.ui.github.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.efe.githubrepolistener.R
import com.efe.githubrepolistener.databinding.GithubListFragmentBinding
import com.efe.githubrepolistener.utils.extensions.setDivider
import com.efe.githubrepolistener.ui.base.BaseFragment
import com.efe.githubrepolistener.ui.github.GithubListAdapter
import com.efe.githubrepolistener.ui.github.GithubViewModel

class GithubListFragment() : BaseFragment() {

    lateinit var binding: GithubListFragmentBinding

    lateinit var viewModel: GithubViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate<GithubListFragmentBinding>(inflater, R.layout.github_list_fragment, container, false).apply {
            lifecycleOwner = this@GithubListFragment
            viewModel = this@GithubListFragment.viewModel
        }

        binding.recyclerViewGithubItems.setDivider(R.drawable.divider_recycler_view)

        binding.recyclerViewGithubItems.adapter =
            GithubListAdapter(mutableListOf()) {
                viewModel.githubRepo.value = it
                viewModel.navigator?.navigateDetails()
            }

        viewModel.githubRepos.observe(viewLifecycleOwner) { githubViewList ->
            (binding.recyclerViewGithubItems.adapter as? GithubListAdapter)?.setList(githubViewList)
        }

        viewModel.isFavoriteChanged.observe(viewLifecycleOwner) {
            binding.recyclerViewGithubItems.adapter?.notifyDataSetChanged()
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
        const val TAG = "GithubListFragment"
    }

}