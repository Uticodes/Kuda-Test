package com.example.kudatest

import android.app.Application
import com.example.kudatest.injection.AppComponent
import com.example.kudatest.injection.AppModule
//import com.example.kudatest.injection.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
//        appComponent = DaggerAppComponent.builder()
//            .appModule(AppModule(this))
//            .build()
    }
}