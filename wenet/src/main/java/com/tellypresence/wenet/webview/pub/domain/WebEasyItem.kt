package com.tellypresence.wenet.webview.pub.domain

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

/**
 * Representation for news item from NHK "Web Easy" website
 */
data class WebEasyItem(
        // News headline, typ. containing kanji/furigana html markup tags
        val titleRaw: String,
        // Date/time of news story (Japanese format)
        val dateStampRaw: String,
        val storyRelUrl: String,
        val storyPhotoUrl: String
) {
    companion object {
        var moshi = Moshi.Builder().build()
        var jsonAdapter = moshi.adapter(WebEasyItem::class.java)

        @FromJson
        fun fromJson(jsonStr: String): WebEasyItem? {
            return jsonAdapter.fromJson(jsonStr)
        }

        @ToJson
        fun toJson(objReprKt: WebEasyItem): String {
            val jsonStr = jsonAdapter.toJson(objReprKt)
            return jsonStr
        }
    }

    @ToJson
    fun toJson(): String {
        val jsonStr = jsonAdapter.toJson(this)
        return jsonStr
    }
}
