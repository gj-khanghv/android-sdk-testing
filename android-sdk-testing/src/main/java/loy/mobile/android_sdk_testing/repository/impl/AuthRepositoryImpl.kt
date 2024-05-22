package loy.mobile.android_sdk_testing.repository.impl

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
}