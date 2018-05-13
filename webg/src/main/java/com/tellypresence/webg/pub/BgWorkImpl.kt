package com.tellypresence.webg.pub

import android.util.Log
import com.tellypresence.webg.workmanager.BgImplWorkMgr

class BgWorkImpl : IBgWork {

    private val TAG = BgWorkImpl::class.java.simpleName

    override fun scrapeWebsite() {
        Log.d(TAG, "scrapeWebsite()")
        val bgImplWorkMgr : IBgWork = BgImplWorkMgr()
        bgImplWorkMgr.scrapeWebsite()
    }
}
