package loy.mobile.androidsdktesting

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import loy.mobile.android_sdk_testing.SdkUtils
import loy.mobile.androidsdktesting.ui.theme.AndroidSdkTestingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidSdkTestingTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val context = LocalContext.current
                    Button(onClick = {
                        SdkUtils.launchSdk(context, launcher)
                    }) {

                    }
                }
            }
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        Log.d("Caller", "Token: ${it.resultCode}, ${it.data?.getStringExtra("token")}")
    }
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