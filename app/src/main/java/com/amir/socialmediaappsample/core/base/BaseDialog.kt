package com.amir.socialmediaappsample.core.base

import android.content.Context
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.amir.socialmediaappsample.R

abstract class BaseDialog<B : ViewDataBinding>(context: Context) : AppCompatDialog(context){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(context),layoutRes,null,false)
        onDialogReady(savedInstanceState,binding as B)
        setContentView(binding.root)
        val window = window
        window?.let {
            val drawable = InsetDrawable(ContextCompat.getDrawable(context, R.drawable.transparent_bg),0)
            it.setBackgroundDrawable(drawable)
            it.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            it.setGravity(Gravity.CENTER)
            it.setWindowAnimations(R.style.MyCustomWindowAnimation)
        }
    }

    @get:LayoutRes
    protected abstract val layoutRes : Int
    protected abstract fun onDialogReady(savedInstanceState : Bundle?,binding : B)
}