package loy.mobile.android_sdk_testing.repository.impl

import kotlinx.coroutines.runBlocking
import loy.mobile.android_sdk_testing.model.UserModel
import loy.mobile.android_sdk_testing.network.ApiService
import loy.mobile.android_sdk_testing.repository.UserRepository

class UserRepositoryImpl(
    private val api: ApiService,
) : UserRepository {
    override fun fetchUserProfile(token: String): UserModel? {
        return runBlocking {
            api.fetchProfile("Bearer $token").body()?.data
        }
    }
}