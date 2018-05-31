package com.github.fatihsokmen.codewars.datasource

data class UserDomain(
        @JvmField val userName: String,
        @JvmField val name: String,
        @JvmField val honor: Int,
        @JvmField val clan: String,
        @JvmField val skills: List<String>
)
