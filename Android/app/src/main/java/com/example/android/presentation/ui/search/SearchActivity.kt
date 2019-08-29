package com.example.android.presentation.ui.search

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.R
import com.example.android.presentation.UserData
import com.example.android.presentation.model.RepoSearchResponse
import com.example.android.presentation.model.User
import com.example.android.presentation.ui.adapter.SearchAdapter
import org.koin.android.ext.android.inject

class SearchActivity : AppCompatActivity(), UserDataList {

    private val presenter: SearchPresenter<UserData> by inject()
    val items by lazy { ArrayList<User>() }

    val searchAdapter by lazy { SearchAdapter(this, items) }

    override fun searchGithubUser(searchWord: String) {
        if (searchWord.isNullOrBlank()) {

        }
    }

    var rvList = findViewById<RecyclerView>(R.id.rv_search_list)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
    }

    override fun onCreateView(name: String?, context: Context?, attrs: AttributeSet?): View? {
        val view = rvList
        view.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity, RecyclerView.VERTICAL, false)
            setHasFixedSize(true)
            adapter = searchAdapter
        }

        return view
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
