package loy.mobile.android_sdk_testing.network

import loy.mobile.android_sdk_testing.model.GetUserProfileResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface ApiService {
    @GET("api-user/member/v1/home-profile")
    suspend fun fetchProfile(@Header("Authorization") token: String): Response<GetUserProfileResponse>
}