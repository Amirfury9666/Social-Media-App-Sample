package com.amir.socialmediaappsample.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amir.socialmediaappsample.extensions.lazyDeferred
import com.amir.socialmediaappsample.model.PostModel
import com.amir.socialmediaappsample.core.repository.AppRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PostViewModel(private val repository: AppRepository) : ViewModel() {

    fun insertPost(postModel: PostModel) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.insertPost(postModel)
        }
    }

    fun updatePost(postModel: PostModel) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.updatePost(postModel)
        }
    }

    fun deletePost(postModel: PostModel) {
        CoroutineScope(Dispatchers.Main).launch {
            repository.deletePost(postModel)
        }
    }

    fun deleteAllPosts() {
        CoroutineScope(Dispatchers.Main).launch {
            repository.deleteAllPosts()
        }
    }

    fun getAllPostsAsync(): Deferred<LiveData<List<PostModel>>> {
        val posts by lazyDeferred {
            repository.getAllPosts()
        }
        return posts
    }
}