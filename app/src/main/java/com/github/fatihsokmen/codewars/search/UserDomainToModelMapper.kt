package com.github.fatihsokmen.codewars.search

import com.github.fatihsokmen.codewars.data.UserDomain
import com.github.fatihsokmen.codewars.search.UserModel.Companion.SEARCH
import io.reactivex.functions.Function
import javax.inject.Inject

class UserDomainToModelMapper @Inject constructor() : Function<UserDomain, UserModel> {

    override fun apply(domain: UserDomain): UserModel =
        UserModel(
                SEARCH,
                domain.userName,
                domain.name,
                domain.leaderboardPosition,
                mapLanguages(domain.ranks.languages.keys)
        )

    private fun mapLanguages(languageSet: Set<String>): String {
        val languages = StringBuilder()
        for (language in languageSet) {
            languages.append("\u2022").append(language).append("  ")
        }
        return languages.toString()
    }
}