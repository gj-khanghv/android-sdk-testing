package loy.mobile.android_sdk_testing.activity

import android.app.Activity
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.webkit.SslErrorHandler
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import loy.mobile.android_sdk_testing.databinding.WebviewBinding


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = WebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val webview = binding.webview
        webview.settings.javaScriptEnabled = true
        webview.settings.allowContentAccess = true
        webview.settings.domStorageEnabled = true
        webview.addJavascriptInterface(JsObject {
            setResult(200, Intent().apply { putExtra("token", it) })
            finish()
        }, "Android")
        webview.setWebViewClient(object : WebViewClient() {
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                super.onReceivedSslError(view, handler, error)
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
        webview.loadUrl(
            "https://iframe-authen.uat.skyjoy.io/?action=signin&client_id=641b66b0-a17d-45c6-b925-9c41513e2ef2",
        )
    }
}