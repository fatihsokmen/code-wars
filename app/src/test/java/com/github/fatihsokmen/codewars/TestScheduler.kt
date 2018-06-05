package com.github.fatihsokmen.codewars

import com.github.fatihsokmen.codewars.dependency.scheduler.Scheduler
import io.reactivex.schedulers.Schedulers


class TestScheduler : Scheduler {

    override fun io(): io.reactivex.Scheduler {
        return Schedulers.trampoline()
    }

    override fun main(): io.reactivex.Scheduler {
        return Schedulers.trampoline()
    }
}


