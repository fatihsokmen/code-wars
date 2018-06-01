package com.github.fatihsokmen.codewars.dependency.resource

import android.support.annotation.StringRes

interface IStringResources {

    fun getString(@StringRes resourceId: Int) : String

    fun getString(@StringRes resourceId: Int, vararg formatArgs: Any): String
}