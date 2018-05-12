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
import com.tellypresence.wenet.webview.pub.domain.WebEasyItem
import org.jsoup.Jsoup

/**
 * Jsoup can't parse dynamic web pages without special help; for this purpose employ a
 * "headless" webView to pre-parse content
 *
 * Known issues
 * May not work on Kitkat 4.4.2 Samsung Galaxy 4
 *
 * Refs
 *      https://stackoverflow.com/questions/39140121/android-parse-js-generated-urls-with-jsoup
 *
 * Steve Madsen, 12 May 2018
 */
class WeWebViewTest(context: Context) : WebView(context), INetContract.INetContractPrivate {

    private val TAG = WeWebViewTest::class.java.simpleName

    private val resultItems = mutableListOf<WebEasyItem>()

    override fun loadWebpage() {
        Log.e(TAG, "loadWebpage()")
        loadUrl("https://www3.nhk.or.jp/news/easy/")
    }

    private inner class JSHtmlInterface {
        @android.webkit.JavascriptInterface
        fun showHTML(html: String) {

//            uiHandler.post(
//                    Runnable {
            val doc = Jsoup.parse(html)
            val elements = doc.getElementsByClass("top-news-list")
            resultItems.clear()
            for (element in elements) {
                val newsItems = element.getElementsByClass("news-list-grid__item news-list-item")
                newsItems?.let {
                    for (newsItem in newsItems) {
                        // Note: title grabs raw html (w/markup) -- defer parsing until human UI display
                        val title = newsItem.getElementsByClass("title").html()
                        val dateTime = newsItem.getElementsByClass("time").text().trim()
                        val figureElement = newsItem.getElementsByClass("news-list-item__image")
                        val linkElement = figureElement[0].getElementsByAttribute("href")[0]
                        val storyRelUrl = linkElement.getElementsByTag("a")[0].attr("href").trim()
                        val storyPhotoUrl = linkElement.getElementsByTag("img")[0].attr("src").trim()
                        resultItems.add(WebEasyItem(title, dateTime, storyRelUrl, storyPhotoUrl))
                    }
                }
            }
            Log.d(TAG, "showHTML(): resultItems: $resultItems")
        }
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
