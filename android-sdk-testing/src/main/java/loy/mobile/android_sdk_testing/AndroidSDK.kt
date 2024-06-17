package loy.mobile.android_sdk_testing

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import loy.mobile.android_sdk_testing.activity.AuthActivity
import loy.mobile.android_sdk_testing.activity.FlightRedemptionActivity
import loy.mobile.android_sdk_testing.activity.PointExchangeActivity
import loy.mobile.android_sdk_testing.model.ExchangeToken
import loy.mobile.android_sdk_testing.model.UserModel
import loy.mobile.android_sdk_testing.repository.AuthRepository
import loy.mobile.android_sdk_testing.repository.UserRepository
import loy.mobile.android_sdk_testing.utils.KoinModules
import org.koin.core.KoinApplication

class AndroidSDK(
    private val env: String,
) {
    private val koinApplication = KoinApplication.init().modules(KoinModules.retrofitModule, KoinModules.repositoryModule)
    private val userRepository: UserRepository by koinApplication.koin.inject()
    private val authRepository: AuthRepository by koinApplication.koin.inject()

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
     * exchange token
     * @param token current system token
     * @param onExchangeToken callback to receive exchange token model
     */
    fun exchangeToken(token: String, onExchangeToken: (ExchangeToken?) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val exchangeToken = authRepository.exchangeToken(token, env)
            onExchangeToken(exchangeToken)
        }
    }

    /**
     * point exchange
     * @param activity activity5
     */
    fun pointExchange(activity: Activity?) {
        CoroutineScope(Dispatchers.IO).launch {
            val token = authRepository.mockTokenForPointExchange(env)
            val intent = Intent(activity, PointExchangeActivity::class.java).apply {
                putExtra("token", token)
                putExtra("env", env)
            }
            Log.d("AAAAA", "Token: $token")
            activity?.startActivity(intent)
        }
    }

    fun flightRedemption(activity: Activity?, token: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val intent = Intent(activity, FlightRedemptionActivity::class.java).apply {
                putExtra("token", token)
                putExtra("env", env)
            }
            Log.d("AAAAA", "Token: $token")
            activity?.startActivity(intent)
        }
    }

    fun userProfile(token: String, onProfileFetch: (String) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            val user = userRepository.fetchUserProfile(token, env)
            onProfileFetch(user)
        }
    }
}