package com.amir.socialmediaappsample.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.amir.socialmediaappsample.R
import com.amir.socialmediaappsample.core.base.BaseActivity
import com.amir.socialmediaappsample.databinding.ActivityCreatePostBinding
import com.amir.socialmediaappsample.extensions.*
import com.amir.socialmediaappsample.model.PostModel
import com.amir.socialmediaappsample.utils.Constants
import com.amir.socialmediaappsample.viewModel.PostViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

private const val RC_PICK_IMAGE = 1993
class CreatePostActivity : BaseActivity<ActivityCreatePostBinding>(),KodeinAware {
    override val kodein: Kodein by kodein()

    private val _viewModel : PostViewModel by instance()

    private var postImage = ""
    private lateinit var mBinding : ActivityCreatePostBinding
    override val layoutRes: Int get() = R.layout.activity_create_post

    override fun getToolbar(binding: ActivityCreatePostBinding): Toolbar? = binding.toolbar

    override fun onActivityReady(instanceState: Bundle?, binding: ActivityCreatePostBinding) {
        mBinding = binding
        enableBack()
        setToolbarTitle(getString(R.string.createPost))
        setClicks(binding)
    }
    private fun setClicks(binding: ActivityCreatePostBinding){
        binding.addPhotoButton.setOnClickListener {
            val intent = Intent(this,GalleryActivity::class.java)
            launchActivityForResult(intent, RC_PICK_IMAGE)
        }

        binding.removePostImageButton.setOnClickListener {
            postImage = ""
            binding.postImageContainer.hide()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_PICK_IMAGE && resultCode == RESULT_OK){
            val image = data?.getStringExtra(Constants.POST_IMAGE)
            image?.let {
                loadImage(mBinding.postImage,it)
                mBinding.postImageContainer.show()
                postImage = it

            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.create_post_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.post -> createNewPost()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createNewPost(){
        val postText = mBinding.postEt.text.toString().trim()
        val date = System.currentTimeMillis().toString()

        if (postText.isNullOrBlank()){
            toast("Please Enter Your Post Text")
            return
        }
        val model = PostModel("John Snow",postText,postImage,date,"0","0","0",false)
        _viewModel.insertPost(model)
        toast(getString(R.string.postUploading))
        finish()
    }
}