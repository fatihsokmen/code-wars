package com.github.fatihsokmen.codewars.challengedetails

import android.view.View
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.data.ChallengeDetailsDomain
import ru.noties.markwon.Markwon

class ChallengeDetailsViewBindings {

    @BindView(R.id.description)
    lateinit var descriptionView: TextView

    fun init(view: View) {
        ButterKnife.bind(this, view)
    }

    fun bind(details: ChallengeDetailsDomain) {
        Markwon.setMarkdown(descriptionView, details.description)
    }
}