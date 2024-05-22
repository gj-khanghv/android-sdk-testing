package loy.mobile.android_sdk_testing.network

import loy.mobile.android_sdk_testing.model.ExchangeToken
import loy.mobile.android_sdk_testing.model.GetUserProfileResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("api-user/member/v1/home-profile")
    suspend fun fetchProfile(@Header("Authorization") token: String): Response<GetUserProfileResponse>

    @GET("api-identity/public/v1/exchange-token")
    suspend fun exchangeToken(@Field("token") token: String): Response<ExchangeToken>
}