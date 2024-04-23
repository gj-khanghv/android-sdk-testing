package loy.mobile.android_sdk_testing

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import loy.mobile.android_sdk_testing.activity.LoginActivity

class SdkUtils {
    companion object {
        /**
         * Launch SDK to get token
         * @param context activity context
         * @param launcher to receive data
         */
        fun launchSdk(context: Context?, launcher: ActivityResultLauncher<Intent>) {
            val intent = Intent(context, LoginActivity::class.java)
            launcher.launch(intent)
        }
    }
}