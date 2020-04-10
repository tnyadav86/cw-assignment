package com.android.coolwinks.di.module

import com.android.coolwinks.flickr.ui.FlickrFragment
import com.android.coolwinks.users.ui.UserFragment
import com.android.coolwinks.users.ui.UserMessageFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun userFragment(): UserFragment

    @ContributesAndroidInjector
    abstract fun userMessageFragment(): UserMessageFragment

    @ContributesAndroidInjector
    abstract fun flickrFragment(): FlickrFragment
}