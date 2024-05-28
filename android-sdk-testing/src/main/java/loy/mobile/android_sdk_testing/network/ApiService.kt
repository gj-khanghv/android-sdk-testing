package loy.mobile.android_sdk_testing.network

import loy.mobile.android_sdk_testing.model.ExchangeToken
import loy.mobile.android_sdk_testing.model.GetUserProfileResponse
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @GET("api-user/member/v1/home-profile")
    suspend fun fetchProfile(@Header("Authorization") token: String): Response<GetUserProfileResponse>

    @GET("api-identity/public/v1/exchange-token")
    suspend fun exchangeToken(@Field("token") token: String): Response<ExchangeToken>

    @FormUrlEncoded
    @POST("api-mockup-adapter/mockup/v1/partners/points/sign-token")
    suspend fun mockTokenForPointExchange(
        @Field("phone") phone: String,
        @Field("memberCode") memberCode: String,
        @Field("iss") iss: String
    ): Response<Map<String, Any>>
}