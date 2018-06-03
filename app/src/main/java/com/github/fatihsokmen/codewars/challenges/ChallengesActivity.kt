package com.github.fatihsokmen.codewars.challenges

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.R
import javax.inject.Inject

class ChallengesActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    lateinit var toolbar: Toolbar
    @BindView(R.id.navigation)
    lateinit var navigationView: BottomNavigationView

    @Inject
    lateinit var adapter: ChallengesResultsAdapter

    private var lastSelectedItemId: Int? = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenges)

        ButterKnife.bind(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navigationView.setOnNavigationItemSelectedListener(this)
        navigationView.selectedItemId = R.id.action_show_completed_challenges
    }

    override fun onNavigationItemSelected(menu: MenuItem): Boolean {
        val itemId = menu.itemId
        if (itemId != lastSelectedItemId) {
            changeFragment(itemId)
            lastSelectedItemId = itemId
        }
        return true
    }

    private fun changeFragment(itemId: Int) {
        val arguments = Bundle()
        val fragment = when (itemId) {
            R.id.action_show_completed_challenges -> {
                val fragment = ChallengesFragment()
                arguments.putSerializable("flow", Flow.COMPLETED_CHALLENGES)
                fragment
            }
            R.id.action_show_authored_challenges -> {
                val fragment = ChallengesFragment()
                arguments.putSerializable("flow", Flow.AUTHORED_CHALLENGES)
                fragment
            }
            else -> throw IllegalArgumentException("Id is not supported")
        }
        fragment.arguments = arguments

        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_content, fragment as Fragment, itemId.toString())
                .commit()
    }

    companion object {

        @JvmStatic
        fun getIntent(context: Context, userName: String) = Intent(context, ChallengesActivity::class.java)
    }
}
