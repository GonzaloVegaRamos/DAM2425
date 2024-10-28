package com.example.dam1016

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dam1016.ui.theme.Dam1016Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)


        val et1=findViewById<EditText>(R.id.et)
        val et3=findViewById<EditText>(R.id.et2)
        val tv=findViewById<TextView>(R.id.tv)
        val butn=findViewById<Button>(R.id.btn)
        butn.setOnClickListener {
            val et1=findViewById<EditText>(R.id.et)
            val nro1 = et1.text.toString().toInt()
            val nro2 = et3.text.toString().toInt()
            val suma = nro1 + nro2
            tv.text = "Resultado: ${suma.toString()}"
        }
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
    Dam1016Theme {
        Greeting("Android")
    }
}