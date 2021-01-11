package com.efe.githubrepolistener.ui.github.fragment

import android.app.Application
import android.content.Context
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
import com.efe.githubrepolistener.utils.extensions.getSharedPreferences

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

        viewModel.githubRepo.value?.let { githubRepo ->
            val sharedPref = activity?.application?.getSharedPreferences()
            val currentValue = sharedPref?.getBoolean(githubRepo.model.id.toString(), false)

            if(currentValue == true) {
                binding.imageViewFavorite.setImageResource(R.drawable.ic_star)
            }
            else {
                binding.imageViewFavorite.setImageResource(R.drawable.ic_star_inactive)
            }

        }

        binding.imageViewFavorite.setOnClickListener {
            viewModel.githubRepo.value?.let { githubRepo ->
                val sharedPref =  activity?.application?.getSharedPreferences()
                val currentValue = sharedPref?.getBoolean(githubRepo.model.id.toString(), false)
                if(currentValue == true) {
                    sharedPref.edit()?.putBoolean(githubRepo.model.id.toString(), false)?.apply()
                    binding.imageViewFavorite.setImageResource(R.drawable.ic_star_inactive)
                    githubRepo.isFavorite = false
                }
                else {
                    sharedPref?.edit()?.putBoolean(githubRepo.model.id.toString(), true)?.apply()
                    binding.imageViewFavorite.setImageResource(R.drawable.ic_star)
                    githubRepo.isFavorite = true
                }
                viewModel.isFavoriteChanged.value = true
            }
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