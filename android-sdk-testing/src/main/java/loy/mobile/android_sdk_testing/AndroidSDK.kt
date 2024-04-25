package loy.mobile.android_sdk_testing

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import loy.mobile.android_sdk_testing.activity.LoginActivity
import loy.mobile.android_sdk_testing.model.UserModel
import loy.mobile.android_sdk_testing.repository.UserRepository
import loy.mobile.android_sdk_testing.utils.KoinModules
import org.koin.core.KoinApplication

class AndroidSDK {
    private val koinApplication = KoinApplication.init().modules(KoinModules.apiModule, KoinModules.retrofitModule, KoinModules.repositoryModule)
    private val userRepository: UserRepository by koinApplication.koin.inject()

    /**
     * Fetch Token
     * @param context activity context
     * @param launcher to receive data
     */
    fun fetchToken(context: Context?, launcher: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, LoginActivity::class.java)
        launcher.launch(intent)
    }

    /**
     * Fetch User Profile
     * @param token access token
     * @return user profile
     */
    fun fetchUserProfile(token: String): UserModel? {
        return userRepository.fetchUserProfile(token)
    }
}