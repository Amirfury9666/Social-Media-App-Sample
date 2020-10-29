package com.amir.socialmediaappsample.utils

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.amir.socialmediaappsample.R
import com.amir.socialmediaappsample.core.glide.GlideApp
import com.amir.socialmediaappsample.extensions.hide
import com.amir.socialmediaappsample.extensions.show
import com.amir.socialmediaappsample.model.PostModel

object BindingUtils {
    @JvmStatic
    @BindingAdapter("loadPostImage")
    fun loadPostImage(imageView: ImageView?, imageUrl: String?) {
        imageView?.context?.let {
            GlideApp.with(it).load(imageUrl).placeholder(R.drawable.image_bg).error(R.drawable.tom).into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("loadUserImage")
    fun loadUserImage(imageView: ImageView?, imageUrl: String?) {
        imageView?.context?.let {
            GlideApp.with(it).load(imageUrl).placeholder(R.drawable.tom).into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("setPostImageVisibility")
    fun postImageVisibility(imageView: ImageView, postModel: PostModel) {
        if (postModel.postImage.isNullOrBlank()) {
            imageView.hide()
        } else {
            imageView.show()
        }
    }

    @JvmStatic
    @BindingAdapter("setPostLikeStatus")
    fun setPostLikeStatus(imageView: ImageView, isLiked: Boolean) {
        if (isLiked) {
            imageView.setImageResource(R.drawable.ic_heart_active)
        } else {
            imageView.setImageResource(R.drawable.ic_heart_in_active)
        }
    }

}