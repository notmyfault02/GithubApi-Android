package com.example.android.presentation.ui.repo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.R
import com.example.android.presentation.model.RepoSearchResponse
import com.example.android.presentation.model.User
import com.example.android.presentation.ui.adapter.RepoAdapter
import com.example.android.presentation.ui.search.UserDataList
import kotlinx.android.synthetic.main.activity_repo.*
import org.koin.android.ext.android.inject

class RepoActivity : AppCompatActivity(), UserDataList {

    private val presenter: RepoPresenter<UserDataList> by inject()

    val repoAdapter by lazy { RepoAdapter(this, ArrayList<User>()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repo)

        rv_repo.adapter = repoAdapter
    }

    override fun onDataLoaded(storeResponse: RepoSearchResponse) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onDataFailed() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun searchGithubUser(searchWord: String) {
        if (searchWord.isNullOrBlank()) {
            repoAdapter.apply {
                items.clear()
                notifyDataSetChanged()
            }
        }
    }
}
