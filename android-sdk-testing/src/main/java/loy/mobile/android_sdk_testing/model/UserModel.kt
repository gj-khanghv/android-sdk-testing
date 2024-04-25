package loy.mobile.android_sdk_testing.model

import com.google.gson.annotations.SerializedName

data class GetUserProfileResponse(
    val statusCode: Int,
    val message: String,
    val data: UserModel
)
data class UserModel(
    val fullName: String,
    val email: String,
)