package com.amir.socialmediaappsample.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "post_table")
data class PostModel(
    val userName: String,
    val postText: String,
    val postImage: String,
    val postDate: String,
    val likes: String,
    val comments: String,
    val shares: String,
    var isLiked : Boolean = false
){
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}