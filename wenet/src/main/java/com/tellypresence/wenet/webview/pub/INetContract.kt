package com.tellypresence.wenet.webview.pub

import android.content.Context

interface INetContract {
    fun loadWebpage(context: Context)

    interface INetContractPrivate {
        fun loadWebpage()
    }
}
