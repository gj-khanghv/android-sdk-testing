# Android SDK Testing
[![](https://jitpack.io/v/gj-khanghv/android-sdk-testing.svg)](https://jitpack.io/#gj-khanghv/android-sdk-testing)

A SDK to access XXX services such as login, sign-up, point exchange, ext..
## Download
You can download a jar from GitHub's [releases page](https://github.com/gj-khanghv/android-sdk-testing/releases).

Or use Gradle:
```Kotlin
repositories {
	google()
	mavenCentral()
	maven { 
		url = uri("https://jitpack.io") 
	}
}

dependencies {
	implementation("com.github.gj-khanghv:android-sdk-testing:release-07")
}
```
## How do I use Android SDK Testing?
Instantiate `AndroidSDK` object:
```Kotlin
private val sdk: AndroidSDK = AndroidSDK()
```
Use provided methods in `sdk` object:
```Kotlin
sdk.signIn(context, launcher)
```
Some methods required a launcher to receive data, create a launcher:
```Kotlin
private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){  
        <your code>  
    }
```
When you receives data with launcher, you can access data inside launcher as below:
```Kotlin
private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){  
        if (it.statusCode == 200) {
		    <your code>
        }
    }
```
Example:
```Kotlin
class MainActivity : ComponentActivity() {  

	private val sdk: AndroidSDK = AndroidSDK()  
	
    override fun onCreate(savedInstanceState: Bundle?) {  
        super.onCreate(savedInstanceState)  
        setContent {  
            AndroidSdkTestingTheme {
                Surface(
                modifier = Modifier.fillMaxSize(), 
                color = MaterialTheme.colorScheme.background
                ) {  
                    val context = LocalContext.current  
                    Column {  
                        Button(onClick = {  
                           sdk.signIn(context, launcher)  
                        }) { }
                    }  
                }            
            }        
        }    
    }  
  
    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){   
        if (it.statusCode == 200) {
	        it.data?.getStringExtra("token")?.let { token ->  
				Toast.makeText(this, token, Toast.LENGTH_SHORT).show()
	        } 
        } 
    }  
}
```
## Author
Khang Huynh - @gj-khanghv on Github
## License
BSD, part MIT and Apache 2.0. See the [LICENSE](https://github.com/gj-khanghv/android-sdk-testing/blob/master/LICENSE) file for details.
