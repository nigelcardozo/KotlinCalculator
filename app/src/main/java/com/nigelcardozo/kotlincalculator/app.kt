package com.nigelcardozo.kotlincalculator

import android.app.Application
import com.nigelcardozo.kotlincalculator.injection.appModule
import org.koin.android.ext.android.startKoin

class app: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(appModule))
    }
}