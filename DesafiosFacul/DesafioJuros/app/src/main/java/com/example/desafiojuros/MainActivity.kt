package com.example.desafiojuros

import android.R.attr.clickable
import android.graphics.Color.green
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafiojuros.ui.theme.DesafioJurosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesafioJurosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaJuros(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaJuros(modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    var valorInicial by remember { mutableStateOf("")}
    var valorFinal by remember { mutableStateOf("R$ 0,00") }
    var taxaJuros by remember { mutableStateOf(0.0) }
    var arredondar by remember { mutableStateOf(false) }



    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFE7FFFF)
    ) {
        Column(
            modifier = Modifier
                .selectableGroup()
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Calculadora de juros",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = modifier.padding(24.dp)
            )
            OutlinedTextField(
                value = valorInicial,
                onValueChange = { valorInicial = it},
                label = { Text("Dinheiro emprestado")},
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { taxaJuros = 0.0 }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = (taxaJuros == 0.0), onClick = { taxaJuros = 0.0})
                Text(
                    text = "Familiar/ Melhor amigo (Sem juros)"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { taxaJuros = 0.05 }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = (taxaJuros == 0.05), onClick = { taxaJuros = 0.05})
                Text(
                    text = "Amigo próximo (5%)"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { taxaJuros = 0.10 }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = (taxaJuros == 0.10), onClick = { taxaJuros = 0.10})
                Text(
                    text = "Colega de trabalho (10%)"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { taxaJuros = 0.25 }
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = (taxaJuros == 0.25), onClick = { taxaJuros = 0.25})
                Text(
                    text = "Desconhecido (25%)"
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Arredondar?",
                    modifier = Modifier.padding(16.dp)
                    )

                Switch(
                    checked = arredondar,
                    onCheckedChange = {arredondar = it}
                )
            }
            Text(
                text = "A pessoa deve te pagar:",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Text(
                text = " $valorFinal",
                fontSize = 30.sp,
                color = Color(0xFF008000),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(30.dp)
            )

            Button(onClick = {
                val base = valorInicial.toDoubleOrNull() ?: 0.0
                var resultado = base + (base * taxaJuros)

                if (arredondar) {
                    resultado = kotlin.math.round(resultado)
                }
                valorFinal = "R$ %.2f".format(resultado)
            },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(32.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE),
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                Text(
                    text = "CALCULAR",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(14.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaJurosPreview() {
    DesafioJurosTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            TelaJuros()
        }
    }
}