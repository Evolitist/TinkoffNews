package com.evolitist.tinkoffnews.view.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.evolitist.tinkoffnews.R
import com.evolitist.tinkoffnews.model.NewsTitle
import kotlinx.android.synthetic.main.news_title.view.*

class ListAdapter(private val onClick: (Int) -> Unit) : RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {
    private val diffCallback = object : DiffUtil.ItemCallback<NewsTitle>() {
        override fun areItemsTheSame(oldItem: NewsTitle, newItem: NewsTitle) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: NewsTitle, newItem: NewsTitle) = oldItem == newItem
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.news_title, parent, false), onClick)
    }

    override fun getItemCount() = differ.currentList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.item = differ.currentList[position]
    }

    fun setData(data: List<NewsTitle>?) = differ.submitList(data)

    class ItemViewHolder(itemView: View, private val onClick: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
        var item: NewsTitle? = null
            set(value) {
                field = value
                titleView.text = HtmlCompat.fromHtml(value?.text ?: "", HtmlCompat.FROM_HTML_MODE_COMPACT)
                timestampView.text = value?.publicationDate?.toString()
            }
        private val timestampView = itemView.news_timestamp
        private val titleView = itemView.news_title

        init {
            itemView.run {
                isClickable = true
                setOnClickListener {
                    item?.id?.let(onClick)
                }
            }
        }
    }
}
