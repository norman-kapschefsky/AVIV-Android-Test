package de.kapschefsky.android.aviv.test.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import de.kapschefsky.android.aviv.test.app.theme.AVIVAndroidTestTheme
import de.kapschefsky.android.aviv.test.core.domain.usecases.RealEstateUseCases
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject lateinit var realEstateUseCases: RealEstateUseCases

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            AVIVAndroidTestTheme {
                val output = remember { mutableStateOf("Start") }

                LaunchedEffect(Unit) {
                    withContext(Dispatchers.IO) {
                        output.value = realEstateUseCases.getRealEstateListItems().fold({ it.toString() }, { it.size.toString() })
                    }
                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    innerPadding.calculateTopPadding()
                    Text(text = output.value, modifier = Modifier.padding(150.dp))
                }
            }
        }
    }
}

@Composable
fun Greeting(
    name: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AVIVAndroidTestTheme {
        Greeting("Android")
    }
}
