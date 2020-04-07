package com.evolitist.tinkoffnews.presenter

import com.evolitist.tinkoffnews.model.NewsTitle
import com.evolitist.tinkoffnews.network.TinkoffNewsAPI
import javax.inject.Inject

class ListPresenter @Inject constructor(newsApi: TinkoffNewsAPI) : BasePresenter<List<NewsTitle>>(newsApi) {
    override fun getData() = newsApi.getList().cache()!!

    override fun transform(value: List<NewsTitle>) = value.sortedByDescending { it.publicationDate }
}
