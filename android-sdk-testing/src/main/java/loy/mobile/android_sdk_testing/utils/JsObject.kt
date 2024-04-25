package loy.mobile.android_sdk_testing.utils

import android.util.JsonReader
import android.util.Log
import android.webkit.JavascriptInterface
import org.json.JSONObject

class JsObject(
    private val onSuccess: (Triple<String, String, String>) -> Unit,
) {
    @JavascriptInterface
    fun receiveMessage(data: String) {
        Log.d("AAAAAA", "Response: $data")
        val json = JSONObject(data)
        if (json.getString("message").toString() == "LOGIN_SUCCESS") {
            val token = json.getJSONObject("data").getString("token")
            val refreshToken = json.getJSONObject("data").getString("refreshToken")
            val expiredTime = json.getJSONObject("data").getString("expired")
            onSuccess.invoke(Triple(token, refreshToken, expiredTime))
        }
    }
}