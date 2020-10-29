package com.amir.socialmediaappsample.adapter

import androidx.recyclerview.widget.DiffUtil
import com.amir.socialmediaappsample.R
import com.amir.socialmediaappsample.core.base.BaseListAdapter
import com.amir.socialmediaappsample.listener.IPostItemClickListener
import com.amir.socialmediaappsample.model.PostModel
import kotlinx.android.synthetic.main.item_post.view.*

class PostListAdapter(private val listener: IPostItemClickListener?) :
    BaseListAdapter<PostModel>(PostDiffCallBack()) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_post
    }

    private class PostDiffCallBack : DiffUtil.ItemCallback<PostModel>() {
        override fun areItemsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return oldItem.userName == newItem.userName && oldItem.postImage == newItem.postImage && oldItem.id == newItem.id && oldItem.likes == newItem.likes && oldItem.postText == newItem.postText && oldItem.isLiked == newItem.isLiked
        }
        override fun areContentsTheSame(oldItem: PostModel, newItem: PostModel): Boolean {
            return oldItem.userName == newItem.userName && oldItem.postImage == newItem.postImage && oldItem.id == newItem.id && oldItem.likes == newItem.likes && oldItem.postText == newItem.postText && oldItem.isLiked == newItem.isLiked
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.binding.root.userImage.setOnClickListener {
            listener?.onUserClick(getItem(position), position)
        }

        holder.binding.root.userName.setOnClickListener {
            listener?.onUserClick(getItem(position), position)
        }

        holder.binding.root.moreOptionsButton.setOnClickListener {
            listener?.onMenuButtonClick(getItem(position), position)
        }

        holder.binding.root.postImage.setOnClickListener {
            listener?.onPostClick(getItem(position), position)
        }

        holder.binding.root.likeButton.setOnClickListener {
            listener?.onLikeButtonClick(getItem(position), position)
        }

        holder.binding.root.commentButton.setOnClickListener {
            listener?.onCommentClick(getItem(position), position)
        }

        holder.binding.root.shareButton.setOnClickListener {
            listener?.onShareClick(getItem(position), position)
        }
    }
}