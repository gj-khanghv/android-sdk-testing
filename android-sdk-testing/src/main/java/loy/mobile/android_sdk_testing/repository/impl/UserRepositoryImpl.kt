package loy.mobile.android_sdk_testing.repository.impl

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import loy.mobile.android_sdk_testing.constant.Api
import loy.mobile.android_sdk_testing.model.UserModel
import loy.mobile.android_sdk_testing.network.ApiService
import loy.mobile.android_sdk_testing.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.create

class UserRepositoryImpl(
    private val apiBuilder: Retrofit.Builder,
) : UserRepository {
    override suspend fun fetchUserProfile(token: String, env: String): String {
        val api = apiBuilder.baseUrl(Api.baseUrlApi(env)).build().create(ApiService::class.java);
        return withContext(Dispatchers.IO) {
            try {
                api.fetchProfile("Bearer $token").body()?.data.toString()
            } catch (e: Exception) {
                e.toString()
            }
        }
    }
}