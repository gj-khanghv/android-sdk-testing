package loy.mobile.android_sdk_testing.repository

import loy.mobile.android_sdk_testing.model.UserModel

interface UserRepository {
    fun fetchUserProfile(token: String): UserModel?
}