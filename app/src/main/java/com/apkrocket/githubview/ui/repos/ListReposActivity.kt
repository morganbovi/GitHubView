package com.apkrocket.githubview.ui.repos

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.apkrocket.githubview.R
import com.apkrocket.githubview.ui.base.BaseActivity
import com.apkrocket.githubview.ui.base.mvibase.MviView
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposIntent.InitialIntent
import com.apkrocket.githubview.ui.repos.ListReposContract.ListReposViewState
import com.apkrocket.githubview.utils.DaggerHolder.Companion.getComponent
import com.apkrocket.githubview.utils.ViewModelFactory
import com.apkrocket.githubview.utils.goneIf
import kotlinx.android.synthetic.main.activity_list_repos.*
import timber.log.Timber
import javax.inject.Inject

class ListReposActivity : BaseActivity(), MviView<ListReposViewState> {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<ListReposViewModel>
    private lateinit var viewModel: ListReposViewModel

    private val repoAdapter = RepoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_repos)
        getComponent().inject(this)

        repo_recycler_view.layoutManager = LinearLayoutManager(this)
        repo_recycler_view.adapter = repoAdapter

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

        if (state.error != null) {
            Timber.e(state.error)
        }

        progress_circular.goneIf(!state.loading)

        repo_recycler_view.goneIf(state.loading)
        repoAdapter.setItems(state.repos)

    }
}