package com.evolitist.tinkoffnews

import android.app.Application
import android.content.Context
import com.evolitist.tinkoffnews.di.components.AppComponent
import com.evolitist.tinkoffnews.di.components.DaggerAppComponent
import com.evolitist.tinkoffnews.di.modules.RetrofitModule
import com.evolitist.tinkoffnews.network.TinkoffNewsAPI
import javax.inject.Inject

class App : Application() {
    private lateinit var component: AppComponent

    @Inject
    lateinit var api: TinkoffNewsAPI

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().retrofitModule(RetrofitModule).build()
        component.inject(this)
    }
}

val Context.app: App get() = applicationContext as App
