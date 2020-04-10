package com.android.coolwinks.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.coolwinks.di.ViewModelKey
import com.android.coolwinks.factory.AppViewModelFactory
import com.android.coolwinks.flickr.viewmodel.FlickrViewModel
import com.android.coolwinks.users.viewmodel.UserMessageViewModel
import com.android.coolwinks.users.viewmodel.UserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(
        factory: AppViewModelFactory
    ): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(UserViewModel::class)
    abstract fun bindUserViewModel(viewmodel: UserViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(UserMessageViewModel::class)
    abstract fun bindUserMessageViewModel(viewmodel: UserMessageViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FlickrViewModel::class)
    abstract fun bindFlickrViewModel(viewmodel: FlickrViewModel): ViewModel
}