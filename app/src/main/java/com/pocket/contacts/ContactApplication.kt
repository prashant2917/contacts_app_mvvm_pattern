package com.pocket.contacts

import android.app.Application
import com.pocket.contacts.di.ApplicationComponent
import com.pocket.contacts.di.DaggerApplicationComponent
import com.pocket.contacts.di.NetworkModule

class ContactApplication : Application() {
    lateinit var component: ApplicationComponent
    override fun onCreate() {
        super.onCreate()

        buildComponent()
    }

    private fun buildComponent() {
        component = DaggerApplicationComponent
            .builder()
            .networkModule(NetworkModule())
            .build()
    }
}