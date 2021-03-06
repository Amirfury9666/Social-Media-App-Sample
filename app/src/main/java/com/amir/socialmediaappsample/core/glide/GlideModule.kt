package com.amir.socialmediaappsample.core.glide

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.module.AppGlideModule

@GlideModule
class GlideModule : AppGlideModule(){
    private val cache = (100 * 1024 * 1024).toLong()
    override fun applyOptions(context: Context, builder: GlideBuilder) {
        builder.setDiskCache(DiskLruCacheFactory(Glide.getPhotoCacheDir(context)?.absolutePath,cache))
        builder.setMemoryCache(LruResourceCache(cache))
    }
}