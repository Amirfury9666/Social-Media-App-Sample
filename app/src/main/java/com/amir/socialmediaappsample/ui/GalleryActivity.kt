package com.amir.socialmediaappsample.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.amir.socialmediaappsample.R
import com.amir.socialmediaappsample.adapter.GalleryAdapter
import com.amir.socialmediaappsample.core.base.BaseActivity
import com.amir.socialmediaappsample.databinding.ActivityGalleryBinding
import com.amir.socialmediaappsample.listener.ICustomItemClickListener
import com.amir.socialmediaappsample.model.GalleryModel
import com.amir.socialmediaappsample.utils.Constants

class GalleryActivity : BaseActivity<ActivityGalleryBinding>(),
    ICustomItemClickListener<GalleryModel> {

    private val adapter = GalleryAdapter()
    override val layoutRes: Int get() = R.layout.activity_gallery

    override fun getToolbar(binding: ActivityGalleryBinding): Toolbar? = binding.toolbar

    override fun onActivityReady(instanceState: Bundle?, binding: ActivityGalleryBinding) {
        enableBack()
        setToolbarTitle(getString(R.string.gallery))
        binding.galleryRecycler.adapter = adapter
        adapter.setClickListener(this)
        setUpGallery()
    }

    private fun setUpGallery() {
        val list = arrayListOf<GalleryModel>()
        val images = resources.getStringArray(R.array.images)
        for (i in images.indices) {
            val model = GalleryModel(images[i])
            list.add(model)
        }
        adapter.submitList(list)
    }

    /** On Gallery Image Selection  */
    override fun onItemClick(id: Int, item: GalleryModel) {
        val image = item.imageUrl

        val intent = Intent()
        intent.putExtra(Constants.POST_IMAGE, image)
        setResult(RESULT_OK, intent)
        finish()
    }

}