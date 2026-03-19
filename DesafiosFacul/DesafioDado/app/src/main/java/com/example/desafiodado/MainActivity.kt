package com.example.desafiodado

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafiodado.ui.theme.DesafioDadoTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesafioDadoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaDoDado(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun TelaDoDado(modifier: Modifier = Modifier) {
    var resultado by remember { mutableIntStateOf(0) }
    var nomeUsuario by remember { mutableStateOf("") }
    val contexto = LocalContext.current

    val imagemDadoId = when (resultado) {
        0 -> R.drawable.dado
        1 -> R.drawable.dado1
        2 -> R.drawable.dado2
        3 -> R.drawable.dado3
        4 -> R.drawable.dado4
        5 -> R.drawable.dado5
        else -> R.drawable.dado6
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF5F5F5)
    ) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = nomeUsuario,
            onValueChange = { nomeUsuario = it },
            label = { Text("Nome") },
            modifier = Modifier.padding(16.dp)
        )

        Text(
            text = if (nomeUsuario.isEmpty()) "Jogue o dado!" else "$nomeUsuario, jogue o dado!",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF333333)
        )

        Text(text = "Descubra o valor do dado", color = Color.Gray)

        Spacer(modifier = Modifier.height(40.dp))

        Text(text = "Aplicativo de dado", fontWeight = FontWeight.Bold, fontSize = 20.sp)

        Image(
            painter = painterResource(id = imagemDadoId),
            contentDescription = "Dado",
            modifier = Modifier.size(180.dp)
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                resultado = (1..6).random()

                Toast.makeText(contexto, "Você tirou $resultado!", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20))
        ) {
            Text(text = "Sortear", color = Color.White)
        }
    }
}
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDoMeuApp() {
    DesafioDadoTheme {
        TelaDoDado()
    }
}