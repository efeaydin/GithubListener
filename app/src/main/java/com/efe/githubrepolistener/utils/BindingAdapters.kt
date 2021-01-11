package com.efe.githubrepolistener.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

class BindingAdapters {

    companion object {
        @JvmStatic
        @BindingAdapter("bind:isVisible")
        fun isVisible(view: View, isVisible: Boolean) {
            view.visibility = if(isVisible) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("app:imageUrl")
        fun setUrl(imageView: ImageView, imageUrl: String?) {
            imageUrl?.let { url ->
                Glide.with(imageView.context)
                    .load(url)
                    .into(imageView)
            }
        }

    }

}