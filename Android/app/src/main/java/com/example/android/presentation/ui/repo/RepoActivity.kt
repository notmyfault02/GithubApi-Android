package com.example.android.presentation.ui.repo

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.presentation.model.RepoSearchResponse
import com.example.android.presentation.ui.adapter.RepoAdapter
import kotlinx.android.synthetic.main.activity_repo.*
import org.koin.android.ext.android.inject

class RepoActivity : AppCompatActivity(), UserDataList {

    private val presenter: RepoPresenter<UserDataList> by inject()

    val repoAdapter by lazy { RepoAdapter(this, ArrayList()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)
        presenter.userData = this

        rv_repo.apply {
            layoutManager = LinearLayoutManager(this@RepoActivity, RecyclerView.VERTICAL, false)
            rv_repo.setHasFixedSize(true)
            rv_repo.adapter = repoAdapter
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_activity_search, menu)
        val searchView = menu?.findItem(R.id.menu_activity_search_query)?.actionView as androidx.appcompat.widget.SearchView
        searchView?.setOnQueryTextListener(object: androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { searchGithubUser(query)}
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })

        return super.onCreateOptionsMenu(menu)
    }

    override fun onDataLoaded(storeResponse: RepoSearchResponse) {
        repoAdapter.apply {
            items.clear()
            items.addAll(storeResponse.items)
            notifyDataSetChanged()
        }
    }

    override fun onDataFailed() {
        repoAdapter.apply {
            items.clear()
            notifyDataSetChanged()
        }
    }

    override fun searchGithubUser(searchWord: String) {
        if (searchWord.isNullOrBlank()) {
            repoAdapter.apply {
                items.clear()
                notifyDataSetChanged()
            }
        } else {
            presenter.getGithubUser(searchWord)
        }
    }
}
