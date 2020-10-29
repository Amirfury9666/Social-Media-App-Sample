package com.amir.socialmediaappsample.viewModelFactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amir.socialmediaappsample.repository.AppRepository
import com.amir.socialmediaappsample.viewModel.PostViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val repository: AppRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        with(modelClass) {
            when {
                isAssignableFrom(PostViewModel::class.java) -> PostViewModel(repository)
                else -> error("Invalid ViewModel")
            }

        } as T
}