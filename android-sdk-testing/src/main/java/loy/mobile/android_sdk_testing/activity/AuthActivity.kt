package loy.mobile.android_sdk_testing.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.IIcon
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.mikepenz.iconics.utils.colorInt
import com.mikepenz.iconics.utils.sizeDp
import loy.mobile.android_sdk_testing.constant.Api
import loy.mobile.android_sdk_testing.databinding.WebviewBinding
import loy.mobile.android_sdk_testing.utils.JsObject


class AuthActivity : AppCompatActivity() {
    private lateinit var binding: WebviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val method = intent.getStringExtra("method") ?: "signIn"

        configToolbar()
        configWebView(method)
    }

    private fun configToolbar() {
        setSupportActionBar(binding.webviewToolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setUpToolbarActionIcon(GoogleMaterial.Icon.gmd_arrow_back_ios)
        }
    }

    private fun setUpToolbarActionIcon(icon: IIcon, color: Int = Color.BLACK, dp: Int = 21) {
        supportActionBar?.apply {
            setHomeAsUpIndicator(IconicsDrawable(this@AuthActivity, icon).apply {
                colorInt = color
                sizeDp = dp
            })
        }
    }

    /**
     * @param method [String] Methods sign in or sign up
     */
    @SuppressLint("SetJavaScriptEnabled")
    private fun configWebView(method: String) {
        binding.webview.apply {
            settings.apply {
                javaScriptEnabled = true
                allowContentAccess = true
                domStorageEnabled = true
            }
            addJavascriptInterface(
                JsObject {
                    setResult(
                        200, Intent().apply {
                            putExtra("token", it.first)
                            putExtra("refreshToken", it.second)
                            putExtra("expired", it.third)
                        })
                    finish()
                },
                "Android"
            )
            setWebViewClient(object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                    return false
                }

                override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                    Log.d("WVERROR", "onReceivedError: ${error?.errorCode}")
                    when (error?.errorCode) {
                        // Redirect history issue
                        -1 -> {
                            view?.stopLoading()
                            binding.webview.goBack()
                        }
                        else -> super.onReceivedError(view, request, error)

                    }
                }
                override fun onPageCommitVisible(view: WebView?, url: String?) {
                    binding.toolbarTitle.text = view?.title
                    val history = binding.webview.copyBackForwardList()
                    supportActionBar?.apply {
                        if (history.currentIndex > 1) {
                            setDisplayHomeAsUpEnabled(true)
                            setUpToolbarActionIcon(GoogleMaterial.Icon.gmd_arrow_back_ios)
                        } else {
                            setDisplayHomeAsUpEnabled(true)
                            setUpToolbarActionIcon(GoogleMaterial.Icon.gmd_close)
                        }
                    }
                    super.onPageCommitVisible(view, url)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    try {
                        binding.webview.loadUrl(
                            """javascript:(function(){window.addEventListener("message", function(event) {
                    if (event.origin !== "https://iframe-authen.uat.skyjoy.io") return;
                    var data = event.data;
                    if (typeof data === 'object') {
                        Android.receiveMessage(JSON.stringify(data));
                    }
                }, false);})()"""
                        )
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    super.onPageFinished(view, url)
                }
            })
            loadUrl(Api.authUrl(method))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val stack = binding.webview.copyBackForwardList()
                if (stack.currentIndex > 1) {
                    binding.webview.goBack()
                } else {
                    finish()
                }
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && binding.webview.copyBackForwardList().currentIndex > 1) {
            binding.webview.goBack()
            return true
        }
        return super.onKeyUp(keyCode, event)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        setIntent(intent)
    }
}