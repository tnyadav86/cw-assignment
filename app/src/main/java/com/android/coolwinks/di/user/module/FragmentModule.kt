package com.android.coolwinks.di.user.module

import com.android.coolwinks.users.ui.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun userFragment(): UserFragment

}