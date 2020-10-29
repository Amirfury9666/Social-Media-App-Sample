package com.amir.socialmediaappsample.core.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.amir.socialmediaappsample.model.PostModel

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: PostModel)

    @Delete()
    fun deletePost(post: PostModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePost(post: PostModel)

    @Query("SELECT * FROM post_table")
    fun getAllPosts(): LiveData<List<PostModel>>

    @Query("DELETE FROM post_table")
    fun deleteAllPosts()
}