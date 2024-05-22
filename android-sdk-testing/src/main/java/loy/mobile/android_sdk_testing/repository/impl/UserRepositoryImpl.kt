package loy.mobile.android_sdk_testing.repository.impl

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import loy.mobile.android_sdk_testing.model.UserModel
import loy.mobile.android_sdk_testing.network.ApiService
import loy.mobile.android_sdk_testing.repository.UserRepository

class UserRepositoryImpl(
    private val api: ApiService,
) : UserRepository {
    override suspend fun fetchUserProfile(token: String): UserModel? {
        return withContext(Dispatchers.IO) {
            try {
                api.fetchProfile("Bearer $token").body()?.data
            } catch (e: Exception) {
                // Handle exception if needed
                null
            }
        }
    }
}