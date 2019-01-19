package net.rubey.yoyocinema.di

import dagger.Component
import net.rubey.yoyocinema.di.modules.AppModule
import net.rubey.yoyocinema.di.modules.DataModule
import net.rubey.yoyocinema.di.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        DataModule::class
    ]
)
interface AppComponent {
}