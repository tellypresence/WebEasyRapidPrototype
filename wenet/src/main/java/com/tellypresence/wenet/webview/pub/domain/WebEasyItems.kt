package com.tellypresence.wenet.webview.pub.domain

import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

data class WebEasyItems(
        val items: MutableList<WebEasyItem> = mutableListOf()
) {
    companion object {
        var moshi = Moshi.Builder().build()
        var jsonAdapter = moshi.adapter(WebEasyItems::class.java)

        @FromJson
        fun fromJson(jsonStr: String): WebEasyItems? {
            return jsonAdapter.fromJson(jsonStr)
        }

        @ToJson
        fun toJson(objReprKt: WebEasyItems): String {
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
