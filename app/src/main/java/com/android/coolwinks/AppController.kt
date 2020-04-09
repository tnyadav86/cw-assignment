package com.android.coolwinks

import com.android.coolwinks.di.user.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

/*
 * we use our AppComponent (now prefixed with Dagger)
 * to inject our Application class.
 * This way a DispatchingAndroidInjector is injected which is
 * then returned when an injector for an activity is requested.
 * */
class AppController : DaggerApplication(){

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }
}
