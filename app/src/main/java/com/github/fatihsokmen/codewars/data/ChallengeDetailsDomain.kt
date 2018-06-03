package com.github.fatihsokmen.codewars.data

data class ChallengeDetailsDomain(
        val id: String,
        val name: String,
        val slug: String,
        val category: String,
        val languages: List<String>,
        val description: String,
        val tags: List<String>
)
