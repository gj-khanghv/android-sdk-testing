package loy.mobile.android_sdk_testing.constant

import android.net.Uri

object Api {
    private const val CLIENT_ID = "641b66b0-a17d-45c6-b925-9c41513e2ef2"
    private const val SIGN_IN = "signin"
    private const val SIGN_UP = "signup"
    private const val ACTION_PARAM = "action"
    private const val CLIENT_ID_PARAM = "client_id"
    private const val AUTHORITY_DEV = "iframe-authen.dev.skyjoy.io"
    private const val AUTHORITY_STG = "iframe-authen.stg.skyjoy.io"
    private const val AUTHORITY_UAT = "iframe-authen.uat.skyjoy.io"
    private const val AUTHORITY_PROD = "iframe-authen.skyjoy.io"
    private val BASE_URL_BUILDER: Uri.Builder = Uri.Builder().scheme("https")
    fun authUrl(method: String, env: String): String {
        val authority = when (env) {
            "dev" -> AUTHORITY_DEV
            "stg" -> AUTHORITY_STG
            "uat" -> AUTHORITY_UAT
            "prod" -> AUTHORITY_PROD
            else -> AUTHORITY_UAT
        }
        val action = when (method) {
            "signIn" -> SIGN_IN
            else -> SIGN_UP
        }
        return if (authority == AUTHORITY_UAT) {
            BASE_URL_BUILDER.clearQuery().authority(authority).appendQueryParameter(CLIENT_ID_PARAM, CLIENT_ID)
                .appendQueryParameter(ACTION_PARAM, action).toString()
        } else {
            BASE_URL_BUILDER.clearQuery().authority(authority)
                .appendQueryParameter(ACTION_PARAM, action).toString()
        }
    }

    fun pointExchange(token: String): String {
        return BASE_URL_BUILDER.clearQuery().authority("partner-app.stg.skyjoy.io").appendQueryParameter("t", token)
            .appendQueryParameter("m", "pointSwapModule").toString()
    }

    fun flightRedemption(token: String): String {
        return BASE_URL_BUILDER.clearQuery().authority("partner-app.stg.skyjoy.io").appendQueryParameter("t", token)
            .appendQueryParameter("m", "bookingModule").toString()
    }
}