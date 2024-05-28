package loy.mobile.android_sdk_testing.repository.impl

import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import loy.mobile.android_sdk_testing.model.ExchangeToken
import loy.mobile.android_sdk_testing.network.ApiService
import loy.mobile.android_sdk_testing.repository.AuthRepository

class AuthRepositoryImpl(
    private val api: ApiService
): AuthRepository {
    override suspend fun exchangeToken(token: String): ExchangeToken? {
        return withContext(Dispatchers.IO) {
            try {
                api.exchangeToken(token).body()
            } catch (e: Exception) {
                null
            }
        }
    }

    override suspend fun mockTokenForPointExchange(): String? {
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