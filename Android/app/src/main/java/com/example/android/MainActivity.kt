package com.example.android

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    lateinit var btnSearch: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSearch = findViewById(R.id.fab_gosearch_main)
        btnSearch.onClick {
            startActivity<SearchActivity>()
        }
    }
}
