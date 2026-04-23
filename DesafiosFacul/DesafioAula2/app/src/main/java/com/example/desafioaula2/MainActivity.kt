package com.example.desafioaula2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafioaula2.ui.theme.DesafioAula2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesafioAula2Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TelaPlayerMusical(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TelaPlayerMusical( modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    var volume by remember { mutableFloatStateOf(0.65f) }
    var musicaAnterior by remember { mutableStateOf(false) }
    var proximaMusica by remember { mutableStateOf(false) }
    var musicaSelecionada by remember { mutableStateOf(false) }
    var musicaTocando by remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF009688)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .selectableGroup()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Meu player musical",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = modifier.padding(16.dp)
            )
            Image(
                painter = painterResource(R.drawable.capa_spotify),
                contentDescription = null
            )
            Text(
                text = "Spotify",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                modifier = modifier.padding(10.dp)
            )
            Slider(
                value = volume,
                onValueChange = { newValue -> volume = newValue },
                valueRange = 0f..1f,
                modifier = modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "0.0",
                    color = Color.White
                )
                Text(
                    text = "Volume: %.2f".format(volume),
                    color = Color.White
                )
                Text(
                    text = "1",
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "A música ${if (musicaTocando) "está" else "não está"} tocando",
                    color = Color.White
                )
                Text(
                    text = "Nome da música",
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = {musicaAnterior = true}){
                    Icon(
                        imageVector = Icons.Default.SkipPrevious,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }

                Button(onClick = {musicaTocando = true}) {
                    Icon(
                        imageVector = Icons.Default.PlayArrow, contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }

                Button(onClick = {musicaTocando = false}) {
                    Icon(
                        imageVector = Icons.Default.Pause,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize),
                    )
                }

                Button(onClick = {proximaMusica = true}) {
                    Icon(
                        imageVector = Icons.Default.SkipNext,
                        contentDescription = null,
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .selectableGroup(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                RadioButton(selected = musicaSelecionada, onClick = { musicaTocando = true})
            }
        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPlayerMusicalPreview() {
    DesafioAula2Theme {
        TelaPlayerMusical()
    }
}