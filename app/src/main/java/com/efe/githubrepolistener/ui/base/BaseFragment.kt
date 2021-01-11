package com.efe.githubrepolistener.ui.base

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel

abstract class BaseFragment: Fragment() {

    private var viewModel: ViewModel? = null

    abstract fun getViewModel(): ViewModel


    override fun onAttach(context: Context) {
        super.onAttach(context)

        viewModel = getViewModel()

    }

}