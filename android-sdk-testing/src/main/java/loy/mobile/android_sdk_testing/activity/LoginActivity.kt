package loy.mobile.android_sdk_testing.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.net.http.SslError
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MenuItem
import android.webkit.SslErrorHandler
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.mikepenz.iconics.IconicsDrawable
import com.mikepenz.iconics.typeface.library.fontawesome.FontAwesome
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.mikepenz.iconics.utils.colorInt
import com.mikepenz.iconics.utils.sizeDp
import loy.mobile.android_sdk_testing.databinding.WebviewBinding
import loy.mobile.android_sdk_testing.utils.JsObject


class LoginActivity : AppCompatActivity() {
    private lateinit var binding: WebviewBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val lBinding = WebviewBinding.inflate(layoutInflater)
        binding = lBinding
        val webview = binding.webview
        setContentView(binding.root)
        setSupportActionBar(binding.webviewToolbar)
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
            setHomeAsUpIndicator(IconicsDrawable(this@LoginActivity, GoogleMaterial.Icon.gmd_arrow_back_ios).apply {
                colorInt = Color.BLACK
                sizeDp = 21
            })
        }
        webview.apply {
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
                            webview.goBack()
                        }
                        else -> super.onReceivedError(view, request, error)

                    }
                }
                override fun onPageCommitVisible(view: WebView?, url: String?) {
                    binding.toolbarTitle.text = view?.title
                    val history = binding.webview.copyBackForwardList()
                    if (history.currentIndex > 1) {
                        supportActionBar?.apply {
                            setDisplayHomeAsUpEnabled(true)
                            setHomeAsUpIndicator(
                                IconicsDrawable(
                                    this@LoginActivity,
                                    GoogleMaterial.Icon.gmd_arrow_back_ios
                                ).apply {
                                    colorInt = Color.BLACK
                                    sizeDp = 21
                                })
                        }
                    } else {
                        supportActionBar?.apply {
                            setDisplayHomeAsUpEnabled(true)
                            setHomeAsUpIndicator(IconicsDrawable(this@LoginActivity, GoogleMaterial.Icon.gmd_close).apply {
                                colorInt = Color.BLACK
                                sizeDp = 21
                            })
                        }
                    }
                    super.onPageCommitVisible(view, url)
                }

                override fun onPageFinished(view: WebView?, url: String?) {
                    try {
                        webview.loadUrl(
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
            loadUrl(
                "https://iframe-authen.uat.skyjoy.io/?action=signin&client_id=641b66b0-a17d-45c6-b925-9c41513e2ef2",
            )
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
}