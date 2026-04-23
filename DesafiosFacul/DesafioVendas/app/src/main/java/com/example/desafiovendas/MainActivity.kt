package com.example.desafiovendas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.desafiovendas.ui.theme.DesafioVendasTheme

// --- 1. MODELO DE DADOS ---
data class Produto(
    val nome: String,
    val marca: String,
    val preco: String,
    val comprador: String,
    val endereco: String
)

// --- 2. CLASSE DE ROTAS (Slide 3) ---
sealed class Screen(val route: String) {
    object Lista : Screen("lista_vendas")
    object Detalhe : Screen("detalhe_vendas/{nome}/{marca}/{preco}/{comprador}/{endereco}")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DesafioVendasTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Chamada do NavHost (Slide 4.2)
                    SetupNavGraph(navController = navController, paddingValues = innerPadding)
                }
            }
        }
    }
}

// --- 3. NAVHOST / MAESTRO (Slide 4 e 4.1) ---
@Composable
fun SetupNavGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        navController = navController,
        startDestination = Screen.Lista.route
    ) {
        // TELA DE LISTA
        composable(route = Screen.Lista.route) {
            ListaVendasScreen(navController = navController, padding = paddingValues)
        }

        // TELA DE DETALHES COM PARÂMETROS
        composable(
            route = Screen.Detalhe.route,
            arguments = listOf(
                navArgument("nome") { type = NavType.StringType },
                navArgument("marca") { type = NavType.StringType },
                navArgument("preco") { type = NavType.StringType },
                navArgument("comprador") { type = NavType.StringType },
                navArgument("endereco") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            // "Pescando" os dados da URL (Slide 4.1)
            val nome = backStackEntry.arguments?.getString("nome") ?: ""
            val marca = backStackEntry.arguments?.getString("marca") ?: ""
            val preco = backStackEntry.arguments?.getString("preco") ?: ""
            val comprador = backStackEntry.arguments?.getString("comprador") ?: ""
            val endereco = backStackEntry.arguments?.getString("endereco") ?: ""

            DetalhesScreen(nome, marca, preco, comprador, endereco)
        }
    }
}

// --- 4. TELAS DE UI ---

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaVendasScreen(navController: NavHostController, padding: PaddingValues) {
    // Lista de carros para o desafio
    val listaCarros = listOf(
        Produto("208 Active", "Peugeot", "99.990,00", "Cauã Aguiar", "Gama, DF"),
        Produto("3008 GT", "Peugeot", "244.990,00", "Bárbara Marques", "Brasília, DF"),
        Produto("T-Cross 200", "Volkswagen", "141.990,00", "Gabriel Tavares", "Taguatinga, DF"),
        Produto("Novo Polo", "Volkswagen", "109.990,00", "Victor Praça", "Ceilândia, DF")
    )

    Scaffold(
        topBar = { TopAppBar(title = { Text("Lista de Vendas") }) },
        modifier = Modifier.padding(padding)
    ) { innerPadding ->
        // LazyColumn é mais performático para listas
        LazyColumn(modifier = Modifier.padding(innerPadding).fillMaxSize()) {
            items(listaCarros) { carro ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    elevation = CardDefaults.cardElevation(4.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(16.dp).fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(text = carro.marca, color = Color.Red, fontWeight = FontWeight.Bold)
                            Text(text = carro.nome, fontSize = 14.sp)
                            Text(text = "R$ ${carro.preco}", fontSize = 12.sp)
                        }
                        Button(onClick = {
                            // Gatilho de Navegação (Slide 5)
                            navController.navigate("detalhe_vendas/${carro.nome}/${carro.marca}/${carro.preco}/${carro.comprador}/${carro.endereco}")
                        }) {
                            Text("Comprar")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DetalhesScreen(nome: String, marca: String, preco: String, comprador: String, endereco: String) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "DADOS DA VENDA", fontSize = 24.sp, fontWeight = FontWeight.ExtraBold, color = Color.Blue)
        Spacer(modifier = Modifier.height(16.dp))

        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(text = "Veículo: $nome ($marca)", fontWeight = FontWeight.Bold)
                Text(text = "Valor Total: R$ $preco")
                HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
                Text(text = "Comprador: $comprador")
                Text(text = "Endereço de Entrega: $endereco")
            }
        }
    }
}

// --- 5. PREVIEWS ---

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ListaVendasPreview() {
    DesafioVendasTheme {
        // Criamos um navController "fake" só para a preview não quebrar
        val navController = rememberNavController()
        ListaVendasScreen(
            navController = navController,
            padding = PaddingValues(0.dp)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DetalhesPreview() {
    DesafioVendasTheme {
        // Passamos dados fictícios para ver como fica o layout
        DetalhesScreen(
            nome = "208 Active",
            marca = "Peugeot",
            preco = "99.990,00",
            comprador = "Cauã Aguiar",
            endereco = "Gama, DF"
        )
    }
}