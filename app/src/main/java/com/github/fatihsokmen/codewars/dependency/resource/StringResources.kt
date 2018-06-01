package com.github.fatihsokmen.codewars.dependency.resource

import android.content.Context
import android.support.annotation.StringRes
import javax.inject.Inject

class StringResources @Inject constructor(private val context: Context) : IStringResources {

    override fun getString(@StringRes resourceId: Int): String =
            context.getString(resourceId)

    override fun getString(resourceId: Int, vararg formatArgs: Any): String =
            context.getString(resourceId, *formatArgs)
}