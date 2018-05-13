package com.tellypresence.webg.workmanager

import android.content.Context
import android.util.Log
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.tellypresence.webg.pub.IBgWork

class BgImplWorkMgr : IBgWork {
    private val TAG = BgImplWorkMgr::class.java.simpleName


    override fun scrapeWebsite() {
        Log.d(TAG, "scrapeWebsite()")
        val compressionWork = OneTimeWorkRequest.Builder(BgWorkMgrWorker::class.java)
                .build()
        WorkManager.getInstance().enqueue(compressionWork)
    }
}
