package com.amir.socialmediaappsample.adapter

import androidx.recyclerview.widget.DiffUtil
import com.amir.socialmediaappsample.R
import com.amir.socialmediaappsample.core.base.BaseListAdapter
import com.amir.socialmediaappsample.model.GalleryModel

class GalleryAdapter : BaseListAdapter<GalleryModel>(GalleryDiffCallBack()) {

    override fun getItemViewType(position: Int): Int {
        return R.layout.item_gallery
    }

    private class GalleryDiffCallBack : DiffUtil.ItemCallback<GalleryModel>() {
        override fun areItemsTheSame(oldItem: GalleryModel, newItem: GalleryModel): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: GalleryModel, newItem: GalleryModel): Boolean {
            return oldItem == newItem
        }
    }
}