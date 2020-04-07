package com.evolitist.tinkoffnews.view.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.ContentLoadingProgressBar
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.evolitist.tinkoffnews.R
import com.evolitist.tinkoffnews.di.components.FragmentComponent
import com.evolitist.tinkoffnews.model.NewsTitle
import com.evolitist.tinkoffnews.presenter.ListPresenter
import com.evolitist.tinkoffnews.view.BaseFragment
import kotlinx.android.synthetic.main.list_fragment.view.*

class ListFragment : BaseFragment<List<NewsTitle>, ListPresenter>() {
    private lateinit var listAdapter: ListAdapter
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var loader: ContentLoadingProgressBar

    override fun setupComponent(component: FragmentComponent) {
        component.injectListFragment(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.list_fragment, container, false)
        loader = rootView.list_loader
        listAdapter = ListAdapter(::handleClick).also {
            rootView.list.run {
                adapter = it
                addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            }
        }
        refreshLayout = rootView.list_refresh.apply {
            setOnRefreshListener { fetchData(true) }
        }
        return rootView
    }

    private fun handleClick(itemId: Int) {
        findNavController().navigate(
            ListFragmentDirections.actionListFragmentToEntryFragment(itemId)
        )
    }

    override fun onCompleted(data: List<NewsTitle>?) {
        listAdapter.setData(data)
    }

    override fun onFinished() {
        loader.hide()
        refreshLayout.run {
            isRefreshing = false
            visibility = View.VISIBLE
        }
    }
}
