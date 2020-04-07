package com.evolitist.tinkoffnews.di.modules

import com.evolitist.tinkoffnews.di.ApplicationScope
import com.evolitist.tinkoffnews.network.TinkoffNewsAPI
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
object RetrofitModule {
    @Provides
    @ApplicationScope
    fun provideNewsApi(retrofit: Retrofit): TinkoffNewsAPI = retrofit.create()

    @Provides
    @ApplicationScope
    fun provideRetrofit(jsonConverter: Converter.Factory, callAdapter: CallAdapter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.tinkoff.ru/v1/")
            .addConverterFactory(jsonConverter)
            .addCallAdapterFactory(callAdapter)
            .build()
    }

    @Provides
    @ApplicationScope
    fun provideJsonConverter(): Converter.Factory = MoshiConverterFactory.create()

    @Provides
    @ApplicationScope
    fun provideCallAdapter(): CallAdapter.Factory = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
}
