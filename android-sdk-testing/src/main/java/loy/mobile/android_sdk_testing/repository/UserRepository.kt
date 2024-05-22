package loy.mobile.android_sdk_testing.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import loy.mobile.android_sdk_testing.model.UserModel

interface UserRepository {
    suspend fun fetchUserProfile(token: String): UserModel?
}