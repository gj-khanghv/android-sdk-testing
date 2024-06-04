package loy.mobile.android_sdk_testing.repository.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import loy.mobile.android_sdk_testing.constant.Api
import loy.mobile.android_sdk_testing.model.ExchangeToken
import loy.mobile.android_sdk_testing.network.ApiService
import loy.mobile.android_sdk_testing.repository.AuthRepository
import retrofit2.Retrofit

class AuthRepositoryImpl(
    private val apiBuilder: Retrofit.Builder
): AuthRepository {
    override suspend fun exchangeToken(token: String, env: String): ExchangeToken? {
        val api = apiBuilder.baseUrl(Api.baseUrlApi(env)).build().create(ApiService::class.java);
        return withContext(Dispatchers.IO) {
            try {
                api.exchangeToken(token).body()
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun mockTokenForPointExchange(env: String): String? {
        val api = apiBuilder.baseUrl(Api.baseUrlApi("dev")).build().create(ApiService::class.java);
        return withContext(Dispatchers.IO) {
            try {
                val map = api.mockTokenForPointExchange("+84393971111", "SJ2502783230", "like-partner-non-prod").body() ?: emptyMap()
                Log.d("AAAAA", "map: $map")
                val data = map["data"] as? Map<String, String>
                data?.get("accessToken")
            } catch (e: Exception) {
                e.toString()
            }
        }
    }
}