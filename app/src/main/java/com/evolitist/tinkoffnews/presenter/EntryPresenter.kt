package com.evolitist.tinkoffnews.presenter

import com.evolitist.tinkoffnews.model.NewsItem
import com.evolitist.tinkoffnews.network.TinkoffNewsAPI
import javax.inject.Inject

class EntryPresenter @Inject constructor(newsApi: TinkoffNewsAPI) : BasePresenter<NewsItem>(newsApi) {
    var id = -1
    override fun getData() = newsApi.getItem(id)
}
