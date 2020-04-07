package com.evolitist.tinkoffnews.di.modules

import com.evolitist.tinkoffnews.di.FragmentScope
import com.evolitist.tinkoffnews.network.TinkoffNewsAPI
import com.evolitist.tinkoffnews.presenter.EntryPresenter
import com.evolitist.tinkoffnews.presenter.ListPresenter
import dagger.Module
import dagger.Provides

@Module
class PresenterModule(private val newsApi: TinkoffNewsAPI) {
    @Provides
    @FragmentScope
    fun provideListPresenter() = ListPresenter(newsApi)

    @Provides
    @FragmentScope
    fun provideEntryPresenter() = EntryPresenter(newsApi)
}
