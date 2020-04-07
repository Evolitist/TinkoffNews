package com.evolitist.tinkoffnews.view.entry

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.evolitist.tinkoffnews.R
import com.evolitist.tinkoffnews.di.components.FragmentComponent
import com.evolitist.tinkoffnews.model.NewsItem
import com.evolitist.tinkoffnews.presenter.EntryPresenter
import com.evolitist.tinkoffnews.view.BaseFragment
import kotlinx.android.synthetic.main.entry_fragment.view.*

class EntryFragment : BaseFragment<NewsItem, EntryPresenter>() {
    private val args by navArgs<EntryFragmentArgs>()
    private lateinit var content: TextView
    private lateinit var title: TextView
    private lateinit var timestamp: TextView

    override fun setupComponent(component: FragmentComponent) {
        component.injectEntryFragment(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val rootView = inflater.inflate(R.layout.entry_fragment, container, false)
        content = rootView.entry_text.apply {
            movementMethod = LinkMovementMethod.getInstance()
        }
        title = rootView.news_title
        timestamp = rootView.news_timestamp
        return rootView
    }

    override fun EntryPresenter.setupRequest() {
        id = args.entryId
    }

    override fun onCompleted(data: NewsItem?) {
        title.text = HtmlCompat.fromHtml(data?.title?.text ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
        content.text = HtmlCompat.fromHtml(data?.content ?: "", HtmlCompat.FROM_HTML_MODE_LEGACY)
        timestamp.text = data?.title?.publicationDate?.toString()
    }

    override fun onError(error: Throwable) {
        super.onError(error)
        findNavController().navigateUp()
    }
}
