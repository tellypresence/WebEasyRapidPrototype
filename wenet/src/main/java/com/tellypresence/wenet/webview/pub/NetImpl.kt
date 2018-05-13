package com.tellypresence.wenet.webview.pub

import android.content.Context
import android.os.Handler
import com.tellypresence.infra.application.WeBaseApp
import com.tellypresence.wenet.webview.WeWebViewTest

class NetImpl : INetContract {

    private val handler = Handler(WeBaseApp.app.applicationContext.mainLooper)

    private fun runOnUiThread(r: Runnable) {
        handler.post(r)
    }

    override fun loadWebpage(context: Context) {
        runOnUiThread(Runnable {
            val weWebViewTest : INetContract.INetContractPrivate = WeWebViewTest(context)
            weWebViewTest.loadWebpage()
        })
    }
}
