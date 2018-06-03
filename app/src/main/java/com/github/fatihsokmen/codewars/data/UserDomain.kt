package com.github.fatihsokmen.codewars.data

import com.github.fatihsokmen.codewars.data.remote.search.Ranks

data class UserDomain(
        @JvmField val userName: String,
        @JvmField val name: String?,
        @JvmField val honor: Int,
        @JvmField val clan: String?,
        @JvmField val leaderboardPosition: String,
        @JvmField val skills: List<String>?,
        @JvmField val ranks: Ranks
)

data class Ranks(
        @JvmField val overall: Rank,
        @JvmField val languages: Map<String, Rank>
)

data class Rank(
        @JvmField val rank: Int,
        @JvmField val name: String,
        @JvmField val color: String,
        @JvmField val score: Int
)
