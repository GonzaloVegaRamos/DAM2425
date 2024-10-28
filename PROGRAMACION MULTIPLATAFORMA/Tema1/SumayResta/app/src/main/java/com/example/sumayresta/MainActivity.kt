package com.example.sumayresta

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.TextView
import android.widget.Button
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.sumayresta.ui.theme.SumayRestaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        // Obtener referencias de los elementos en la vista
        val et1 = findViewById<EditText>(R.id.et)
        val et3 = findViewById<EditText>(R.id.et2)
        val tv = findViewById<TextView>(R.id.tv)
        val butn = findViewById<Button>(R.id.btn)
        val spin = findViewById<Spinner>(R.id.spinner)
        val rbS = findViewById<RadioButton>(R.id.rbSumar)
        val rbR = findViewById<RadioButton>(R.id.rbRestar)

        // Crear lista de opciones para el Spinner
        val opciones = listOf("Sumar", "Restar", "Multiplicar", "Dividir")

        // Configurar el adaptador del Spinner
        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spin.adapter = adaptador

        // Configurar el listener del botón
        butn.setOnClickListener {
            realizarOperacion(et1, et3, tv, spin)
        }
    }


    private fun realizarOperacion(et1: EditText, et3: EditText, tv: TextView, spin: Spinner) {

        val nro1 = et1.text.toString().toDoubleOrNull()
        val nro2 = et3.text.toString().toDoubleOrNull()


        //if (nro1 == null || nro2 == null) {
        //  Toast.makeText(this, "Por favor, ingresa números válidos", Toast.LENGTH_SHORT).show()
        //  return
        //}


        val operacionSeleccionada = spin.selectedItem.toString()

        // Realizar la operación según la opción seleccionada
        val resultado = when (operacionSeleccionada) {
            "Sumar" -> nro1!! + nro2!!
            "Restar" -> nro1!! - nro2!!
            "Multiplicar" -> nro1!! * nro2!!
            "Dividir" -> {
                if (nro2 != 0.0) nro1!! / nro2!! else {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                    return
                }
            }
            else -> 0.0
        }

        // Mostrar el resultado en el TextView
        tv.text = "Resultado: $resultado"
    }
}


//if(rbS.isChecked){
                    //val suma = nro1 + nro2
                    //tv.text = "Resultado: ${suma.toString()}"
                //}else{
                    //val resta = nro1 - nro2
                    //tv.text = "Resultado: ${resta.toString()}"
                    //}









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
    SumayRestaTheme() {
        Greeting("Android")
    }
}