package com.example.android.presentation.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import com.example.android.BuildConfig
import com.example.android.R
import com.example.android.data.remote.AuthTokenProvider
import com.example.android.data.remote.provideAuthApi
import com.example.android.model.GithubAccessToken
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInActivity : AppCompatActivity() {

    lateinit var btnStart: Button

    lateinit var btnTest: Button
    lateinit var progress: ProgressBar
    internal val api by lazy { provideAuthApi() }
    internal val authTokenProvider by lazy { AuthTokenProvider(this) }
    lateinit var accessTokenCall: Call<GithubAccessToken>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        btnStart = findViewById(R.id.btn_signin_github)

        btnStart.setOnClickListener {
            val authUri = Uri.Builder().scheme("https")
                .authority("github.com")
                .appendPath("login")
                .appendPath("oauth")
                .appendPath("authorize")
                .appendQueryParameter("client_id",
                    BuildConfig.GITHUB_CLIENT_ID
                )
                .build()

            val intent = CustomTabsIntent.Builder().build()
            intent.launchUrl(this@SignInActivity, authUri)

        }

        btnTest = findViewById(R.id.button_test)

        btnTest.onClick { startActivity<MainActivity>() }


        if (null != authTokenProvider.token) {
            launchMainActivity()
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        showProgress()

        val uri = intent?.data
        if (null == uri) {
            throw IllegalArgumentException("No data exists")
        }

        val code = uri.getQueryParameter("code")
        if (null == code)
            throw IllegalStateException("No code exists")

        getAccessToken(code)
    }

//    private fun getAccessToken(code: String) {
//        showProgress()
//
//        accessTokenCall = api.getAccessToken(
//            BuildConfig.GITHUB_CLIENT_ID, BuildConfig.GITHUB_CLIENT_SECRET, code)
//
//        accessTokenCall.enqueue(object: Callback<GithubAccessToken> {
//            override fun onResponse(call: Call<GithubAccessToken>, response: Response<GithubAccessToken>) {
//                hideProgress()
//
//                val token: GithubAccessToken? = response.body()
//                if (response.isSuccessful() && null != token) {
//                    authTokenProvider.updateToken(token.accessToken)
//                    Log.d("login", "okay")
//
//                    launchMainActivity()
//                } else {
//                    showError(IllegalStateException(
//                        "Not successful: " + response.message()
//                    ))
//                    Log.d("login", "what")
//                }
//            }
//
//            override fun onFailure(call: Call<GithubAccessToken>, t: Throwable) {
//                hideProgress()
//                showError(t)
//                Log.d("login", "failure")
//            }
//        })
//    }

    private fun getAccessToken(code: String) {
        showProgress()

        accessTokenCall = api.getAccessToken(
            BuildConfig.GITHUB_CLIENT_ID, BuildConfig.GITHUB_CLIENT_SECRET, code)

        accessTokenCall!!.enqueue(object : Callback<GithubAccessToken> {
            override fun onResponse(call: Call<GithubAccessToken>,
                                    response: Response<GithubAccessToken>) {
                hideProgress()

                val token = response.body()
                if (response.isSuccessful && null != token) {
                    authTokenProvider.updateToken(token.accessToken)

                    launchMainActivity()
                } else {
                    showError(IllegalStateException(
                        "Not successful: " + response.message()))
                }
            }

            override fun onFailure(call: Call<GithubAccessToken>, t: Throwable) {
                hideProgress()
                showError(t)
            }
        })
    }

    private fun showProgress() {
        btn_signin_github.visibility = View.GONE
        pgBar_signin_github.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        btn_signin_github.visibility = View.VISIBLE
        pgBar_signin_github.visibility = View.GONE
    }

    private fun showError(throwable: Throwable) {
        longToast(throwable.message ?: "No message available")
    }

    private fun launchMainActivity() {
        startActivity(intentFor<MainActivity>().clearTask().newTask())
    }
}
