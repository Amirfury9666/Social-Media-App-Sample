package com.amir.socialmediaappsample.listener

import com.amir.socialmediaappsample.model.PostModel

interface IPostItemClickListener {
    fun onLikeButtonClick(postModel: PostModel,position : Int)
    fun onCommentClick(postModel: PostModel,position: Int)
    fun onShareClick(postModel: PostModel,position: Int)
    fun onUserClick(postModel: PostModel,position: Int)
    fun onMenuButtonClick(postModel: PostModel,position: Int)
    fun onPostClick(postModel: PostModel,position: Int)
}