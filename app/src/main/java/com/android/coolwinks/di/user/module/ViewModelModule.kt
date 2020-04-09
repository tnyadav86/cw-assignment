package com.android.coolwinks.di.user.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.coolwinks.factory.AppViewModelFactory
import com.android.coolwinks.di.ViewModelKey
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
    abstract fun bindViewModel(viewmodel: UserViewModel): ViewModel
}