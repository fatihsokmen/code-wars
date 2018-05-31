package com.github.fatihsokmen.codewars.dependency

import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    AppModule::class,
    NetModule::class,
    DataSourceModule::class])
@Singleton
interface AppComponent : BaseComponent {

    @Component.Builder
    interface Builder : BaseComponent.Builder<AppComponent, Builder>
}
