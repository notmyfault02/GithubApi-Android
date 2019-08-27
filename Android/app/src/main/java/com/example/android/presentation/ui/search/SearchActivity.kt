package com.example.android.presentation.ui.search

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.presentation.SearchAdapter
import com.example.android.presentation.UserData
import com.example.android.presentation.model.RepoSearchResponse
import com.example.android.presentation.model.User
import org.koin.android.ext.android.inject

class SearchActivity : AppCompatActivity(), UserDataList {

    private val presenter: SearchPresenter<UserData> by inject()
    val items by lazy { ArrayList<User>() }

    val searchAdapter by lazy { SearchAdapter(this, items) }

    override fun searchGithubUser(searchWord: String) {
        if (searchWord.isNullOrBlank()) {

        }
    }

    private

    var rvList = findViewById<RecyclerView>(R.id.rv_search_list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = searchAdapter

    }

    override fun onDataLoaded(storeResponse: RepoSearchResponse) {
        rvList.adapter!!.apply {
            items.clear()
            items.addAll(storeResponse.items)
            notifyDataSetChanged()
        }
    }

    override fun onDataFailed() {
        Log.d("test", "onDataFailed")
        rvList.adapter!!.apply {
            items.clear()
            notifyDataSetChanged()
        }
    }
}
