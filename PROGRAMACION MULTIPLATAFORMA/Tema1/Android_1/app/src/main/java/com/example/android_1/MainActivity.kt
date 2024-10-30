import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

                // Llama a la función de contenido principal
                MainContent()

        }
    }
}

@Composable
fun MainContent() {
    // Usamos un Column para centrar los elementos
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Greeting("Android")
        Spacer(modifier = Modifier.height(16.dp))
        MyButton()
    }
}

@Composable
fun MyButton() {
    Button(onClick = {
        // Muestra el mensaje en el Logcat
        Log.d("MainActivity", "¡Botón pulsado!")
    }) {
        Text(text = "Pulsa aquí")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

        MainContent()

}
