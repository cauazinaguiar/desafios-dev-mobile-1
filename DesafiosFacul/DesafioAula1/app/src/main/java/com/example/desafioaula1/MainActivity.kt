package com.example.desafioaula1

import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.desafioaula1.ui.theme.DesafioAula1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesafioAula1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column{
                        CartaoAnimado(
                            name = "Yasmin",
                            modifier = Modifier.padding(innerPadding)
                        )
                        CartaoAnimado(
                            name = "Cauã",
                            modifier = Modifier.padding(innerPadding)
                        )
                        CartaoAnimado(
                            name = "Evellyn",
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CartaoAnimado(name: String, modifier: Modifier = Modifier) {
    var expandido by remember { mutableStateOf(false) }
        Surface(
            modifier = modifier
                .fillMaxWidth()
                .padding(2.dp),
            shape = RoundedCornerShape(5.dp),
            color = MaterialTheme.colorScheme.surfaceVariant,
            tonalElevation = 4.dp,
            shadowElevation = 4.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(5.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(2.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Superficie3(name = name)
                    Button(onClick = {expandido = !expandido}) {
                        Text("Clique aqui")
                    }
                }
                if (expandido) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Image(
                        painter = painterResource(id= R.drawable.ftcaua),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .padding(8.dp)
                    )
                }
            }
        }
}

@Composable
fun Superficie3(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Preview
@Composable
private fun Superficie3Preview() {
    DesafioAula1Theme {
        Superficie3("Yasmin")
    }
}

@Preview
@Composable
private fun CartaoAnimadoPreview() {
    DesafioAula1Theme {
        CartaoAnimado(name = "Yasmin")
    }
}
