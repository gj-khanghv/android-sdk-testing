package loy.mobile.android_sdk_testing

import android.app.Activity
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

class AndroidSDK(
    private val env: String,
) {
    private val koinApplication = KoinApplication.init().modules(KoinModules.apiModule, KoinModules.retrofitModule, KoinModules.repositoryModule)
    private val userRepository: UserRepository by koinApplication.koin.inject()

    /**
     * signIn flow
     * @param context activity context
     * @param launcher to receive data
     */
    fun signIn(context: Context?, launcher: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra("method", "signIn")
            putExtra("env", env)
        }
        launcher.launch(intent)
    }

    /**
     * signIn flow
     * @param activity activity
     */
    fun signIn(activity: Activity?) {
        val intent = Intent(activity, AuthActivity::class.java).apply {
            putExtra("method", "signIn")
            putExtra("env", env)
        }
        activity?.startActivityForResult(intent, 1)
    }

    /**
     * signUp flow
     * @param context activity context
     * @param launcher to receive data
     */
    fun signUp(context: Context?, launcher: ActivityResultLauncher<Intent>) {
        val intent = Intent(context, AuthActivity::class.java).apply {
            putExtra("method", "signUp")
            putExtra("env", env)
        }
        launcher.launch(intent)
    }

    /**
     * signUp flow
     * @param activity activity
     */
    fun signUp(activity: Activity?) {
        val intent = Intent(activity, AuthActivity::class.java).apply {
            putExtra("method", "signUp")
            putExtra("env", env)
        }
        activity?.startActivityForResult(intent, 1)
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