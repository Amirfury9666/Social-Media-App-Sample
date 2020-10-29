package com.amir.socialmediaappsample.core.repository

import androidx.lifecycle.LiveData
import com.amir.socialmediaappsample.model.PostModel

interface AppRepository {
    suspend fun insertPost(postModel: PostModel)

    suspend fun updatePost(postModel: PostModel)

    suspend fun deletePost(postModel: PostModel)

    suspend fun deleteAllPosts()

    suspend fun getAllPosts(): LiveData<List<PostModel>>
}