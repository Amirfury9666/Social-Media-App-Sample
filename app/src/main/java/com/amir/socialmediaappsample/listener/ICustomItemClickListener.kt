package com.amir.socialmediaappsample.listener

interface ICustomItemClickListener<T> {
    fun onItemClick(id : Int,t : T)

}