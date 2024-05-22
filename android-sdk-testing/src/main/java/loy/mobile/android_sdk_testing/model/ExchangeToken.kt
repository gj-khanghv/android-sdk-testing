package loy.mobile.android_sdk_testing.model

import com.google.gson.annotations.SerializedName

data class ExchangeToken(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("refresh_token")
    val refreshToken: String,
    @SerializedName("expires_in")
    val expiresIn: Long,
    @SerializedName("refresh_expires_in")
    val refreshExpiresIn: Long,
    @SerializedName("token_type")
    val tokenType: String,
)