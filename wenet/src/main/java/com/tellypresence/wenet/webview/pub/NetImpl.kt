package com.tellypresence.wenet.webview.pub

import android.content.Context
import com.tellypresence.wenet.webview.WeWebViewTest

class NetImpl : INetContract {

    override fun loadWebpage(context: Context) {
        val weWebViewTest : INetContract.INetContractPrivate = WeWebViewTest(context)
        weWebViewTest.loadWebpage()
    }
}
