package loy.mobile.android_sdk_testing

import android.content.Context
import android.content.Intent
import android.widget.Toast

class SdkUtils {
    companion object {
        fun showToast(ctx: Context?, msg: String) {
            Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show()
        }

        fun launchSdk(ctx: Context?) {
        Intent(ctx, MainActivity::class.java).also { ctx?.startActivity(it) }
        }
    }
}