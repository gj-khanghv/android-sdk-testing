package loy.mobile.android_sdk_testing.constant

import android.net.Uri

object Api {
    private const val CLIENT_ID = "641b66b0-a17d-45c6-b925-9c41513e2ef2"
    private const val SIGN_IN = "signin"
    private const val SIGN_UP = "signup"
    private const val ACTION_PARAM = "action"
    private const val CLIENT_ID_PARAM = "client_id"
    private val BASE_URL_BUILDER: Uri.Builder = Uri.Builder().scheme("https").authority("iframe-authen.uat.skyjoy.io")
    fun authUrl(method: String): String {
        val action = when (method) {
            "signIn" -> SIGN_IN
            else -> SIGN_UP
        }
        return BASE_URL_BUILDER.appendQueryParameter(CLIENT_ID_PARAM, CLIENT_ID).appendQueryParameter(ACTION_PARAM, action).toString()
    }
}