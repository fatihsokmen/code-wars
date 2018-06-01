package com.github.fatihsokmen.codewars.dependency


import android.app.Application
import android.content.Context

import com.github.fatihsokmen.codewars.datasource.local.UserDao
import com.github.fatihsokmen.codewars.dependency.resource.IStringResources
import com.github.fatihsokmen.codewars.dependency.scheduler.Scheduler

import dagger.BindsInstance
import retrofit2.Retrofit

interface BaseComponent {

    fun applicationContext(): Context

    fun scheduler(): Scheduler

    fun retrofit(): Retrofit

    fun stringResources(): IStringResources

    fun userDao(): UserDao

    interface Builder<C : BaseComponent, B : Builder<C, B>> {

        @BindsInstance
        fun application(application: Application): B

        fun build(): C
    }
}
