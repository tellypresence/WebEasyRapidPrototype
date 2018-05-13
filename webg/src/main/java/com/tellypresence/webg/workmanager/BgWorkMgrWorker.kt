package com.tellypresence.webg.workmanager

import android.util.Log
import androidx.work.Worker
import com.tellypresence.infra.application.WeBaseApp
import com.tellypresence.wenet.webview.pub.NetImpl

class BgWorkMgrWorker : Worker() {

    private val TAG = BgWorkMgrWorker::class.java.simpleName

    override fun doWork(): WorkerResult {
        Log.e(TAG, "doWork()")
        val netImpl = NetImpl()
        netImpl.loadWebpage(WeBaseApp.app.applicationContext)
        return WorkerResult.SUCCESS
    }
}
