package com.evolitist.tinkoffnews.model

import com.squareup.moshi.JsonClass
import java.text.SimpleDateFormat

@JsonClass(generateAdapter = true)
data class Date(val milliseconds: Long) : Comparable<Date> {
    override fun toString(): String = SimpleDateFormat.getDateTimeInstance().format(java.util.Date(milliseconds))
    override fun compareTo(other: Date) = milliseconds.compareTo(other.milliseconds)
}

@JsonClass(generateAdapter = true)
data class NewsTitle(
    val id: Int,
    val name: String,
    val text: String,
    val publicationDate: Date,
    val bankInfoTypeId: Int
) {
    override fun equals(other: Any?): Boolean = id == (other as? NewsTitle)?.id
    override fun hashCode(): Int = id
}

@JsonClass(generateAdapter = true)
data class NewsItem(
    val title: NewsTitle,
    val creationDate: Date,
    val lastModificationDate: Date,
    val content: String,
    val bankInfoTypeId: Int,
    val typeId: String
)

@JsonClass(generateAdapter = true)
class Response<T>(val resultCode: String, val payload: T?, val trackingId: String)
