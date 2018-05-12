package com.tellypresence.wenet.webview.pub.domain

/**
 * Representation for news item from NHK "Web Easy" website
 */
data class WebEasyItem(
        // News headline, typ. containing kanji/furigana html markup tags
        val titleRaw: String,
        // Date/time of news story (Japanese format)
        val dateStampRaw: String,
        val storyRelUrl: String,
        val storyPhotoUrl: String
) {
}
