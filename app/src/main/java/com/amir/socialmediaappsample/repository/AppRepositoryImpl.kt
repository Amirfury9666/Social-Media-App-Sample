package com.amir.socialmediaappsample.repository

import androidx.lifecycle.LiveData
import com.amir.socialmediaappsample.db.PostDao
import com.amir.socialmediaappsample.model.PostModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepositoryImpl(private val postDao: PostDao) : AppRepository {
    override suspend fun insertPost(postModel: PostModel) {
        withContext(Dispatchers.IO) {
            postDao.insertPost(postModel)
        }
    }

    override suspend fun updatePost(postModel: PostModel) {
        withContext(Dispatchers.IO) {
            postDao.updatePost(postModel)
        }
    }

    override suspend fun deletePost(postModel: PostModel) {
        withContext(Dispatchers.IO) {
            postDao.deletePost(postModel)
        }
    }

    override suspend fun getAllPosts(): LiveData<List<PostModel>> {
        return withContext(Dispatchers.IO) {
            postDao.getAllPosts()
        }
    }

    override suspend fun deleteAllPosts() {
        withContext(Dispatchers.IO) {
            postDao.deleteAllPosts()
        }
    }
}