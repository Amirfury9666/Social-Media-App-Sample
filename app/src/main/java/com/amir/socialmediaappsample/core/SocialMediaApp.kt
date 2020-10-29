package com.amir.socialmediaappsample.core

import android.app.Application
import com.amir.socialmediaappsample.db.AppDataBase
import com.amir.socialmediaappsample.db.PostDao
import com.amir.socialmediaappsample.repository.AppRepository
import com.amir.socialmediaappsample.repository.AppRepositoryImpl
import com.amir.socialmediaappsample.viewModel.PostViewModel
import com.amir.socialmediaappsample.viewModelFactory.ViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class SocialMediaApp : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@SocialMediaApp))
        bind() from singleton { AppDataBase(instance()) }
        bind<PostDao>() with singleton { instance<AppDataBase>().postDao() }
        bind<AppRepository>() with singleton { AppRepositoryImpl(instance()) }
        bind() from singleton { PostViewModel(instance()) }

        bind() from provider {
            ViewModelFactory(instance())
        }
    }
}