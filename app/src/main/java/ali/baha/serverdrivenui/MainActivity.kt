package ali.baha.serverdrivenui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ali.baha.serverdrivenui.ui.theme.ServerDrivenUiTheme
import ali.baha.serverdrivenui.viewmodel.ServerDrivenUiViewModel
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : ServerDrivenUiViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            lifecycleScope.launch {
                viewModel.getServerDrivenUiData().catch {
                    Toast.makeText(applicationContext,it.message + "  qq",Toast.LENGTH_SHORT).show()

                }.collect {
                    Toast.makeText(applicationContext,it.toString() + " mm",Toast.LENGTH_SHORT).show()
                }
            }


            ServerDrivenUiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ServerDrivenUiTheme {
        Greeting("Android")
    }
}