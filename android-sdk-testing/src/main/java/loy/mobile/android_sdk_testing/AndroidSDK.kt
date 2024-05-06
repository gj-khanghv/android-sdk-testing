package loy.mobile.android_sdk_testing

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import androidx.activity.result.ActivityResultLauncher
import loy.mobile.android_sdk_testing.activity.AuthActivity
import loy.mobile.android_sdk_testing.model.UserModel
import loy.mobile.android_sdk_testing.repository.UserRepository
import loy.mobile.android_sdk_testing.utils.KoinModules
import org.koin.core.KoinApplication

class AndroidSDK {
    private val koinApplication = KoinApplication.init().modules(KoinModules.apiModule, KoinModules.retrofitModule, KoinModules.repositoryModule)
    private val userRepository: UserRepository by koinApplication.koin.inject()

    /**
     * signIn flow
     * @param context activity context
     * @param launcher to receive data
     */
    fun signIn(context: Context?, launcher: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, AuthActivity::class.java).apply {
            setFlags(FLAG_ACTIVITY_NEW_TASK)
            putExtra("method", "signIn")
        }
        launcher.launch(intent)
    }

    /**
     * signUp flow
     * @param context activity context
     * @param launcher to receive data
     */
    fun signUp(context: Context?, launcher: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, AuthActivity::class.java).apply {
            setFlags(FLAG_ACTIVITY_NEW_TASK)
            putExtra("method", "signUp")
        }
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