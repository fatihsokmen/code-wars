package com.github.fatihsokmen.codewars.search

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import butterknife.ButterKnife
import com.github.fatihsokmen.codewars.R

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        ButterKnife.bind(this)

        setSupportActionBar(findViewById(R.id.toolbar))
    }
}
