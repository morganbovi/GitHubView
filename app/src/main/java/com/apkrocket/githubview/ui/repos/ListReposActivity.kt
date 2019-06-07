package com.apkrocket.githubview.ui.repos

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.apkrocket.githubview.ui.base.BaseActivity
import com.apkrocket.githubview.ui.base.mvibase.MviView
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposIntent.InitialIntent
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposViewState
import com.apkrocket.githubview.utils.DaggerHolder.Companion.getComponent
import com.apkrocket.githubview.utils.ViewModelFactory
import timber.log.Timber
import javax.inject.Inject

class ListReposActivity : BaseActivity(), MviView<ListReposViewState> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ListReposViewModel>
    private lateinit var viewModel: ListReposViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getComponent().inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ListReposViewModel::class.java)

        viewModel.states().observe(this, Observer(this::render))

    }

    override fun onResume() {
        super.onResume()
        viewModel.processIntent(InitialIntent)
    }

    override fun render(state: ListReposViewState) {
        Timber.e(state.toString())
        Toast.makeText(this, state::class.java.name, Toast.LENGTH_LONG).show()
    }
}