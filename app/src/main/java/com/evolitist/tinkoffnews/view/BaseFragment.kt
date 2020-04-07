package com.evolitist.tinkoffnews.view

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.evolitist.tinkoffnews.app
import com.evolitist.tinkoffnews.di.components.DaggerFragmentComponent
import com.evolitist.tinkoffnews.di.components.FragmentComponent
import com.evolitist.tinkoffnews.di.modules.PresenterModule
import com.evolitist.tinkoffnews.presenter.BasePresenter
import dagger.Lazy
import javax.inject.Inject

abstract class BaseFragment<D, P : BasePresenter<D>> : Fragment() {
    private lateinit var component: FragmentComponent

    @Inject
    lateinit var presenter: Lazy<P>

    init {
        retainInstance = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component = DaggerFragmentComponent.builder()
            .presenterModule(PresenterModule(context!!.app.api))
            .build()
        setupComponent(component)
        assert(::presenter.isInitialized) {
            "Presenter must be initialized by this point"
        }
    }

    override fun onStart() {
        super.onStart()
        fetchData(false)
    }

    protected fun fetchData(force: Boolean) {
        presenter.get().run {
            setupRequest()
            subscribe(force, ::onCompleted, ::onError, ::onFinished)
        }
    }

    abstract fun setupComponent(component: FragmentComponent)

    open fun P.setupRequest() {}

    abstract fun onCompleted(data: D?)

    open fun onError(error: Throwable) {
        Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
    }

    open fun onFinished() {}
}
