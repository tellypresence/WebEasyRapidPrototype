package com.tellypresence.wenet.webview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.tellypresence.infra.application.WeBaseApp
import com.tellypresence.wenet.R
import com.tellypresence.wenet.webview.pub.INetContract
import org.jsoup.Jsoup

/**
 * Jsoup can't parse dynamic web pages without special help; for this purpose employ a
 * "headless" webView to pre-parse content
 *
 * Refs
 *      https://stackoverflow.com/questions/39140121/android-parse-js-generated-urls-with-jsoup
 *
 * Steve Madsen, 12 May 2018
 */
class WeWebViewTestSanity(context: Context) : WebView(context), INetContract.INetContractPrivate {

    private val TAG = WeWebViewTestSanity::class.java.simpleName

    override fun loadWebpage() {
        Log.e(TAG, "loadWebpage()")
        loadUrl("https://srulad.com/#page-1")
    }

    private inner class JSHtmlInterface {
        @android.webkit.JavascriptInterface
        fun showHTML(html: String) {

//            uiHandler.post(
//                    Runnable {
                        val doc = Jsoup.parse(html)
            // doc: |Webpage not available Webpage not available The webpage at
            // http://srulad.com/#page-1 might be temporarily down or it may have moved permanently to a new web address.
            // Suggestions: Make sure you have a data connection Reload this webpage later Check the address you entered|
            Log.e(TAG, "showHTML(): doc: |${doc.text().trim()}|")
                        val elements = doc.select("#online_movies > div > div")
            Log.e(TAG, "showHTML(): elements: |$elements|")

//                        entries.clear()
                        for (element in elements) {
                            val title = element.select("div.l-description.float-left > div:nth-child(1) > a").first().attr("title")
                            Log.e(TAG, "showHTML(): title: $title")
                            val imgUrl = element.select("div.l-image.float-left > a > img.lazy").first().attr("data-original")
                            Log.e(TAG, "showHTML(): imgUrl: $imgUrl")
//                            entries.add(title + "\n" + imgUrl)
                        }
//                        adapter.notifyDataSetChanged()
                    }
//            )
//        }
    }

    init {
        setVisibility(View.INVISIBLE)
        setLayerType(View.LAYER_TYPE_NONE,null)
        settings.setJavaScriptEnabled(true)
        settings.setBlockNetworkImage(true)
        settings.setDomStorageEnabled(false)
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE)
        settings.setLoadsImagesAutomatically(false)
        settings.setGeolocationEnabled(false)
        settings.setSupportZoom(false)

        addJavascriptInterface(JSHtmlInterface(), "JSBridge")

        webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                //                        progressDialog.show();
                var faviconChk = favicon
                if (faviconChk == null) {
                    faviconChk = BitmapFactory.decodeResource(WeBaseApp.app.resources, R.drawable.ic_dummy_favicon)
                }
                super.onPageStarted(view, url, faviconChk)
                Log.e(TAG, "onPageStarted()")
            }

            override fun onPageFinished(view: WebView, url: String) {
                Log.e(TAG, "onPageFinished()")
                loadUrl("javascript:window.JSBridge.showHTML('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>');")
                //                        progressDialog.dismiss();
            }
        }
    }
}
