package com.tellypresence.webg.pub

import android.content.Context
import com.tellypresence.wenet.webview.pub.NetImpl

class BgWorkImpl : IBgWork {

    override fun scrapeWebsite(context: Context) {
        val netImpl = NetImpl()
        netImpl.loadWebpage(context)
    }
}
