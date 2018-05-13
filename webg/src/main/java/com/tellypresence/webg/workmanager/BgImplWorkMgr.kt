package com.tellypresence.webg.workmanager

import android.content.Context
import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.tellypresence.webg.pub.IBgWork

class BgImplWorkMgr : IBgWork {
    private val TAG = BgImplWorkMgr::class.java.simpleName

    companion object {
        const val WM_TAG_SCRAPE_WEBSITE = "BgImplWorkMgr::scrape_website"
    }

    override fun scrapeWebsite() {
        Log.d(TAG, "scrapeWebsite()")
        val compressionWork = OneTimeWorkRequest.Builder(BgWorkMgrWorker::class.java)
                .addTag(WM_TAG_SCRAPE_WEBSITE)
                .build()
        WorkManager.getInstance().enqueue(compressionWork)
    }
}
