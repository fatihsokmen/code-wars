package com.github.fatihsokmen.codewars.search

import com.github.fatihsokmen.codewars.data.UserDomain
import com.github.fatihsokmen.codewars.search.UserModel.Companion.RECENT
import io.reactivex.functions.Function
import javax.inject.Inject

class UserHistoryDomainToModelMapper @Inject constructor() : Function<List<UserDomain>, List<UserModel>> {

    override fun apply(userDomains: List<UserDomain>): List<UserModel> {
        val models = mutableListOf<UserModel>()

        models.add(UserModel(RECENT))

        userDomains.forEach { domain ->
            models.add(mapUser(domain))
        }
        return models
    }

    private fun mapUser(domain: UserDomain): UserModel =
            UserModel(
                    UserModel.HISTORY,
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