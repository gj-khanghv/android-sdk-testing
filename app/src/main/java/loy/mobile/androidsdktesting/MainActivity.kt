package loy.mobile.androidsdktesting

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
//import loy.mobile.android_sdk_testing.AndroidSDK
import loy.mobile.androidsdktesting.ui.theme.AndroidSdkTestingTheme


class MainActivity : ComponentActivity() {
    private var shortPress = false
    private var longPress = false
//    private val sdk: AndroidSDK = AndroidSDK(env = "stg")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSdkTestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val context = LocalContext.current
                    Column {
                        Button(onClick = {
//                            sdk.signIn(this@MainActivity, launcher)
                        }) {

                        }
                    }
                }
            }
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        Log.d("Caller", "Token: ${it.resultCode}, ${it.data?.getStringExtra("token")}")
        it.data?.getStringExtra("token")?.let { token ->
//            sdk.flightRedemption(this@MainActivity, token)
//            val data = sdk.fetchUserProfile(this, token)
//            Log.d("AAAAA", "Name: ${data?.email}")
        }
    }

//    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//            event?.startTracking();
//            if (longPress) {
//                shortPress = false;
//            } else {
//                shortPress = true;
//                longPress = false;
//            }
//
//            return true;
//        }
//        return super.onKeyDown(keyCode, event)
//    }
//
//    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//            event?.startTracking();
//            if (shortPress) {
//                Toast.makeText(this, "Short Press", Toast.LENGTH_SHORT).show();
//                //Short Press code goes here
//            }
//            shortPress = true;
//            longPress = false;
//            return true;
//        }
//        return super.onKeyUp(keyCode, event)
//    }
//
//    override fun onKeyLongPress(keyCode: Int, event: KeyEvent?): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
//            Toast.makeText(this, "Long Press", Toast.LENGTH_SHORT).show();
//            //Long Press code goes here
//            shortPress = false;
//            longPress = true;
//            return true;
//        }
//        return super.onKeyDown(keyCode, event)
//    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidSdkTestingTheme {
        Greeting("Android")
    }
}