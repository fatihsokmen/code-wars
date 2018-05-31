package com.github.fatihsokmen.codewars.dependency.scheduler

interface Scheduler {

    fun io(): io.reactivex.Scheduler

    fun main(): io.reactivex.Scheduler
}