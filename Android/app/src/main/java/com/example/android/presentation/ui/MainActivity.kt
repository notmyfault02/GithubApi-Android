package com.example.android.presentation.ui

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.android.R
import com.example.android.presentation.ui.repo.RepoActivity
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    lateinit var btnRepo: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRepo = findViewById(R.id.bt_main_gorepo)
        btnRepo.onClick {
            startActivity<RepoActivity>()
        }
    }
}
