package com.evolitist.tinkoffnews.di.components

import com.evolitist.tinkoffnews.App
import com.evolitist.tinkoffnews.di.ApplicationScope
import com.evolitist.tinkoffnews.di.modules.RetrofitModule
import dagger.Component

@ApplicationScope
@Component(modules = [RetrofitModule::class])
interface AppComponent {
    fun inject(app: App)
}
