package com.evolitist.tinkoffnews.network

import com.evolitist.tinkoffnews.model.NewsItem
import com.evolitist.tinkoffnews.model.NewsTitle
import com.evolitist.tinkoffnews.model.Response
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface TinkoffNewsAPI {
    @GET("news")
    fun getList(): Single<Response<List<NewsTitle>>>

    @GET("news_content")
    fun getItem(@Query("id") id: Int): Single<Response<NewsItem>>
}
