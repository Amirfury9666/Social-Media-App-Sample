package com.amir.socialmediaappsample.extensions

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.amir.socialmediaappsample.R
import com.amir.socialmediaappsample.core.glide.GlideApp
import com.bumptech.glide.Glide
import kotlinx.coroutines.*

fun <T> lazyDeferred(block : suspend CoroutineScope.() -> T) : Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY){
            block.invoke(this)
        }
    }
}

fun FragmentActivity.launchActivity(intent: Intent){
    startActivity(intent)
    overridePendingTransition(R.anim.slide_from_bottom,R.anim.slide_to_bottom)
}

fun FragmentActivity.launchActivityForResult(intent: Intent,requestCode : Int){
    startActivityForResult(intent,requestCode)
    overridePendingTransition(R.anim.slide_from_bottom,R.anim.slide_to_bottom)

}

fun View.hide(){
    visibility = View.GONE
}

fun View.show(){
    visibility = View.VISIBLE
}

fun Context.toast(message : String?){
    Toast.makeText(this,message.toString(),Toast.LENGTH_SHORT).show()
}

fun Any.loadImage(imageView: ImageView?, imageUrl : String){
    imageView?.context?.let {
        GlideApp.with(it).load(imageUrl).placeholder(R.drawable.image_bg).into(imageView)
    }
}