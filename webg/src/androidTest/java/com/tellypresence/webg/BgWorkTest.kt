package com.tellypresence.webg

import android.support.test.runner.AndroidJUnit4
import com.tellypresence.webg.workmanager.BgImplWorkMgr
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BgWorkTest {

    @Test
    fun sanityTestWork() {
        val bgImplWorkMgr = BgImplWorkMgr()
        bgImplWorkMgr.scrapeWebsite()
    }
}
