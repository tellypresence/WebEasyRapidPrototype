package com.tellypresence.wenet

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.tellypresence.wenet.webview.WeWebViewTest
import com.tellypresence.wenet.webview.pub.INetContract
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WebViewTest {
    val appContext = InstrumentationRegistry.getTargetContext()

    @Test
    fun sanityTestWeWebView() {
        val weWebViewTest : INetContract.INetContractPrivate = WeWebViewTest(appContext)
        weWebViewTest.loadWebpage()
    }
}
