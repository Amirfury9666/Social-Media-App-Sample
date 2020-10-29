package com.amir.socialmediaappsample.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import com.amir.socialmediaappsample.R
import com.amir.socialmediaappsample.adapter.PostListAdapter
import com.amir.socialmediaappsample.core.base.BaseActivity
import com.amir.socialmediaappsample.databinding.ActivityHomeBinding
import com.amir.socialmediaappsample.extensions.hide
import com.amir.socialmediaappsample.extensions.launchActivity
import com.amir.socialmediaappsample.extensions.show
import com.amir.socialmediaappsample.extensions.toast
import com.amir.socialmediaappsample.listener.IPostItemClickListener
import com.amir.socialmediaappsample.model.PostModel
import com.amir.socialmediaappsample.ui.dialog.PostOptionsDialog
import com.amir.socialmediaappsample.viewModel.PostViewModel
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance
import java.util.*

class HomeActivity : BaseActivity<ActivityHomeBinding>(), KodeinAware, IPostItemClickListener {

    override val kodein: Kodein by kodein()
    private val _viewModel: PostViewModel by instance()
    private val adapter = PostListAdapter(this)

    override val layoutRes: Int get() = R.layout.activity_home

    override fun getToolbar(binding: ActivityHomeBinding): Toolbar? = binding.toolbar

    override fun onActivityReady(instanceState: Bundle?, binding: ActivityHomeBinding) {
        setToolbarTitle(getString(R.string.socialMediaSample))
        setClicks(binding)
        binding.recycler.adapter = adapter
        binding.recycler.addOnScrollListener(ScrollListener(binding))
        fetchPostList(binding)
    }

    private fun setClicks(binding: ActivityHomeBinding) {
        binding.addPostButton.setOnClickListener {
            val intent = Intent(this, CreatePostActivity::class.java)
            launchActivity(intent)
        }

    }

    private fun fetchPostList(binding: ActivityHomeBinding) = launch {
        val posts = _viewModel.getAllPostsAsync().await()
        posts.observe(this@HomeActivity, {
            if (it.isNullOrEmpty()) {
                binding.noPostTv.show()
            } else {
                binding.noPostTv.hide()
                Collections.reverse(it)
                adapter.submitList(it)
                Handler().postDelayed({ binding.recycler.smoothScrollToPosition(0) }, 2000)
            }
        })
    }

    private class ScrollListener(private val binding: ActivityHomeBinding) :
        RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            if (dy > 0) {
                binding.addPostButton.hide()
            } else {
                binding.addPostButton.show()
            }
            super.onScrolled(recyclerView, dx, dy)
        }
    }

    override fun onLikeButtonClick(postModel: PostModel, position: Int) {
        updatePost(postModel)
        toast("Like Clicked")
    }

    override fun onCommentClick(postModel: PostModel, position: Int) {
        toast("Comment Clicked")
    }

    override fun onShareClick(postModel: PostModel, position: Int) {
        toast("Share Clicked")
    }

    override fun onUserClick(postModel: PostModel, position: Int) {
        toast("User Clicked")
    }

    override fun onMenuButtonClick(postModel: PostModel, position: Int) {
        PostOptionsDialog(this, _viewModel, postModel).show()
    }

    override fun onPostClick(postModel: PostModel, position: Int) {
        toast("Post Clicked")
    }

    private fun updatePost(postModel: PostModel) {
        postModel.isLiked = !postModel.isLiked
        _viewModel.updatePost(postModel)
    }


}