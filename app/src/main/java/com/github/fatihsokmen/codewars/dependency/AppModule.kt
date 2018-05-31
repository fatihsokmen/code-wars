package com.github.fatihsokmen.codewars.dependency


import android.app.Application
import android.content.Context
import com.github.fatihsokmen.codewars.dependency.scheduler.Scheduler
import com.github.fatihsokmen.codewars.dependency.scheduler.SchedulerImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun bindScheduler(impl: SchedulerImpl): Scheduler

    @Binds
    @Singleton
    abstract fun bindApplication(impl: Application): Context

}
