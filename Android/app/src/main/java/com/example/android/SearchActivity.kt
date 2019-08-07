package com.example.android

import android.os.Bundle
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.android.api.GithubApi
import com.example.android.model.RepoSearchResponse

class SearchActivity : AppCompatActivity() {

    lateinit var rvList: RecyclerView
    lateinit var progress: ProgressBar
    lateinit var tvMessage: TextView
    lateinit var menuSearch: MenuItem
    lateinit var searchView: SearchView
//    lateinit var adapter by lazy {
//        SearchAdapter().apply { setItemClickListener(this@SearchActivity) }
//    }
    lateinit var api: GithubApi
    lateinit var searchCall: retrofit2.Call<RepoSearchResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        rvList = findViewById(R.id.rv_search_list)
        progress = findViewById(R.id.pgBar_search)
        tvMessage = findViewById(R.id.tv_search_message)

//        adapter = SearchAdapter()
//        rvList.layoutManager = LinearLayoutManager(this)
//        rvList.adapter = adapter

    }
}
