package com.evolitist.tinkoffnews.presenter

import com.evolitist.tinkoffnews.model.Response
import com.evolitist.tinkoffnews.network.TinkoffNewsAPI
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable

abstract class BasePresenter<D>(val newsApi: TinkoffNewsAPI) {
    private lateinit var cachedData: Single<Response<D>>
    private lateinit var disposable: Disposable

    protected abstract fun getData(): Single<Response<D>>

    fun subscribe(
        force: Boolean,
        onSuccess: (D?) -> Unit,
        onError: (Throwable) -> Unit = {},
        onFinished: () -> Unit = {}
    ) {
        if (force || !::cachedData.isInitialized) {
            cachedData = getData()
        }
        disposable = cachedData
            .observeOn(AndroidSchedulers.mainThread())
            .doFinally(onFinished)
            .map { transform(it.payload ?: throw Exception("No data received!")) }
            .subscribe(onSuccess, onError)
    }

    open fun transform(value: D): D = value

    fun onCleared() {
        disposable.takeUnless { it.isDisposed }?.dispose()
    }
}
