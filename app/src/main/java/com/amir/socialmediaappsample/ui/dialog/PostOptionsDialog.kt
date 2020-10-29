package com.amir.socialmediaappsample.ui.dialog

import android.content.Context
import android.os.Bundle
import com.amir.socialmediaappsample.R
import com.amir.socialmediaappsample.core.base.BaseDialog
import com.amir.socialmediaappsample.databinding.DialogPostOptionsBinding
import com.amir.socialmediaappsample.extensions.toast
import com.amir.socialmediaappsample.model.PostModel
import com.amir.socialmediaappsample.viewModel.PostViewModel

class PostOptionsDialog(context: Context,private val viewModel : PostViewModel, private val item : PostModel) : BaseDialog<DialogPostOptionsBinding>(context) {
    override val layoutRes: Int
        get() = R.layout.dialog_post_options

    override fun onDialogReady(savedInstanceState: Bundle?, binding: DialogPostOptionsBinding) {
        binding.deletePost.setOnClickListener {
            viewModel.deletePost(item)
            dismiss()
            toast("Post Deleted...")
        }

        binding.deleteAllPost.setOnClickListener {
            viewModel.deleteAllPosts()
            toast("All Posts Deleted")
            dismiss()
        }

        binding.cancel.setOnClickListener {
            dismiss()
        }
    }

    private fun toast(message : String){
        context.toast(message)
    }
}