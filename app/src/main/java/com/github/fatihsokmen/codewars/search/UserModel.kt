package com.github.fatihsokmen.codewars.search

/**
 * Model classes is used for UI bindings
 */
class UserModel(
        val type: Int,
        val userName: String = "",
        val name: String? = null,
        val leaderboardPosition: String = "",
        val languages: String = ""
) {
    companion object {
        const val RECENT = 0
        const val HISTORY = 1
        const val SEARCH = 2
    }
}


