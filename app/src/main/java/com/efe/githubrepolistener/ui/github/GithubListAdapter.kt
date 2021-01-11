package com.efe.githubrepolistener.ui.github

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.efe.githubrepolistener.R
import com.efe.githubrepolistener.data.model.GithubView
import com.efe.githubrepolistener.databinding.GithubListItemViewHolderBinding

class GithubListAdapter(private var githubItems: List<GithubView>, val onClick: (GithubView) -> Unit): RecyclerView.Adapter<GithubListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            DataBindingUtil.inflate<GithubListItemViewHolderBinding>(
                LayoutInflater.from(parent.context),
                R.layout.github_list_item_view_holder, parent, false
            )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return githubItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(githubItems[position])
    }

    inner class ViewHolder(private val binding: GithubListItemViewHolderBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(githubItem: GithubView) {

            binding.githubModel = githubItem
            binding.viewHolder = this

        }

        fun itemClicked(githubItem: GithubView) {
            onClick(githubItem)
        }

    }

    fun setList(githubItems: List<GithubView>) {
        this.githubItems = githubItems
        notifyDataSetChanged()
    }

}