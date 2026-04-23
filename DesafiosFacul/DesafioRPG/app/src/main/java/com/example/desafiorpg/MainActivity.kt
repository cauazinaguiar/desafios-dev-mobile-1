package com.example.desafiorpg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.desafiorpg.ui.theme.DesafioRPGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesafioRPGTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    JogoRpg(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun JogoRpg(modifier: Modifier = Modifier) {

    val scrollState = rememberScrollState()

    var cenaJogo by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFF121212)
    ) {
        Box(modifier = Modifier.fillMaxSize().padding(24.dp)) {
            Text(
                text = "JOGO DO RPG",
                color = Color.Cyan,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 16.dp)
            )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                text = "AS ESCOLHAS DA VIDA",
                color = Color.Cyan,
                fontSize = 20.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            when (cenaJogo) {
                0 -> CenaRPG(
                    texto = "Você tem 10 anos e tem o poder de escolher entre o CR7 e o Messi para ser seu idolo para o resto da vida. Qual escolha você faz?",
                    opcoes = listOf(
                        "CR7" to { cenaJogo = 1 },
                        "Messi" to { cenaJogo = 2 }
                    )
                )
                1 -> CenaRPG(
                    texto = "Opa, você escolheu o CR7, boa escolha pela dedicação dele, porém o Messi é melhor e você vai sofrer por conta disso, você tem a ultima chance de escolher o real GOAT.",
                    opcoes = listOf(
                        "continuar com o CR7" to { cenaJogo = 3 },
                        "mudar para o real GOAT (Messi)" to { cenaJogo = 4 }
                    )
                )
                2 -> CenaRPG(
                    texto = "Opa, você escolheu o Messi, ótima escolha, ele realmente é a escolha certa a se fazer, então continue com ele que você não vai se arrepender.",
                    opcoes = listOf(
                        "continuar com o real GOAT (Messi)" to { cenaJogo = 4 },
                        "mudar para o fraco do CR7(NÃO FAÇA ISSO!!)" to { cenaJogo = 3 }
                    )
                )
                3 -> CenaFinal(
                    imagemId = R.drawable.cr7fraco,
                    menssagem = "Mds, você ainda escolheu esse cara para ser o fã ate morrer, boa sorte sofrendo com ele",
                    cor = Color.Red,
                    aoRecomecar = { cenaJogo = 0 }
                )
                4 -> CenaFinal(
                    imagemId = R.drawable.messicolete,
                    menssagem = "Boa, você fez a escolha certa, agora disfrute o real GOAT ganhando tudo que pode e te trazendo alegria",
                    cor = Color.Green,
                    aoRecomecar = { cenaJogo = 0 }
                )
            }
        }
    }
}
}
@Composable
fun CenaRPG(texto: String, opcoes: List<Pair<String, () -> Unit>>) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF00ACC1)
        ),
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = texto,
            color = Color.Black,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(20.dp)
        )
    }
    Spacer(modifier = Modifier.height(32.dp))

    opcoes.forEach { (rotulo, acao) ->
        Button(
            onClick = acao,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00ACC1)
            )
        ) {
            Text(
                text = rotulo,
                color = Color.Black,
                fontSize = 20.sp,
            )
        }
    }
}

@Composable
fun CenaFinal(imagemId: Int? = null, menssagem: String, cor: Color, aoRecomecar: () -> Unit) {

    if (imagemId != null) {
        Image(
            painter = painterResource(id = imagemId),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp)
        )
    }

    Text(
        text = menssagem,
        color = cor,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Spacer(modifier = Modifier.height(40.dp))

    OutlinedButton(
        onClick = aoRecomecar,
        border = androidx.compose.foundation.BorderStroke(2.dp, Color.White),
    ) {
        Text("RECOMEÇAR", color = Color.White)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    DesafioRPGTheme {
        JogoRpg()
    }
}