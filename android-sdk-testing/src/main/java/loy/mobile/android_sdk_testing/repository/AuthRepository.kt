package loy.mobile.android_sdk_testing.repository

import loy.mobile.android_sdk_testing.model.ExchangeToken

interface AuthRepository {
    suspend fun exchangeToken(token: String, env: String) : ExchangeToken?

    suspend fun mockTokenForPointExchange(env: String): String?
}