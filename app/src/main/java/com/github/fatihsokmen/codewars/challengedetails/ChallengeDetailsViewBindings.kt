package com.github.fatihsokmen.codewars.challengedetails

import android.support.design.widget.Snackbar
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.R
import com.github.fatihsokmen.codewars.data.ChallengeDetailsDomain
import ru.noties.markwon.Markwon

class ChallengeDetailsViewBindings {

    @BindView(R.id.progress)
    lateinit var progress: ProgressBar
    @BindView(R.id.description)
    lateinit var descriptionView: TextView

    fun init(view: View) {
        ButterKnife.bind(this, view)
    }

    fun bind(details: ChallengeDetailsDomain) {
        Markwon.setMarkdown(descriptionView, details.description)
    }

    fun showProgress(show: Boolean) {
        progress.visibility = if (show) View.VISIBLE else View.GONE
    }

    fun showError(message: String?) {
        message?.let {
            showMessage(it)
        } ?: showMessage("An error occurred")
    }

    private fun showMessage(message: String) {
        Snackbar.make(descriptionView, message, Snackbar.LENGTH_SHORT).show()
    }

}