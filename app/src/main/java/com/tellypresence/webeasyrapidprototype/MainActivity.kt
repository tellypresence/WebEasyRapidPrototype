package com.tellypresence.webeasyrapidprototype

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.tellypresence.webeasyrapidprototype.ui.main.MainFragment
import com.tellypresence.webg.pub.BgWorkImpl

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow()
        }

        val bgWorkImpl = BgWorkImpl()
        bgWorkImpl.scrapeWebsite()
    }
}
