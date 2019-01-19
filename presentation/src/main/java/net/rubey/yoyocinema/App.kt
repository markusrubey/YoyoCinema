package net.rubey.yoyocinema

import android.app.Application
import net.rubey.yoyocinema.di.AppComponent
import net.rubey.yoyocinema.di.DaggerAppComponent
import net.rubey.yoyocinema.di.modules.AppModule
import net.rubey.yoyocinema.di.modules.DataModule
import net.rubey.yoyocinema.di.modules.NetworkModule

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initDependencies()
    }

    private fun initDependencies() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .networkModule(NetworkModule(getString(R.string.api_base_url), getString(R.string.api_key)))
            .dataModule(DataModule())
            .build()
    }
}