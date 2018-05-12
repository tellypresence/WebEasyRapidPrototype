package com.tellypresence.infra.application

import android.app.Application

/**
 * Facilitate "Clean" architecture by allowing inner modules (which don't know about android system
 * implementation details like "Context" and "Activity") to access e.g. resources etc via static app
 * object -- crude but effective; after all seems like a platform contract violation if app's
 * "Application" wasn't available
 */
class WeBaseApp : Application() {

    companion object Instance {
        lateinit var app : WeBaseApp
    }

    override fun onCreate() {
        super.onCreate()
        app = this
    }
}
