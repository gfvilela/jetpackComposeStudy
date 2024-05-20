package br.dev.gustavovilela.firstprojectcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import br.dev.gustavovilela.firstprojectcompose.R
import br.dev.gustavovilela.firstprojectcompose.ui.theme.FirstProjectComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FirstProjectComposeTheme {
                FirstScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun FirstScreen() {
        val currentContext = LocalContext.current
        Scaffold(
            topBar = {
                CustomAppBar()
            },
            content = { padding ->
                Surface(modifier = Modifier.padding(padding)) {
                    HomeScreen(
                        title = currentContext.getString(R.string.title),
                        textButton = currentContext.getString(R.string.textButton),
                        contentText = "5 ponto por real",
                        lifecycle = lifecycleScope,
                        context = LocalContext.current
                    )
                }
            }
        )
    }

    @Composable
    fun Content() {
        val currentContext = LocalContext.current
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp)
                .background(color = Color.LightGray),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentContext.getString(R.string.title)
            )

            Text(
                text = "Teste! sdhfsjf sdfsd"
            )

            Text(
                text = "José da Silva"
            )
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CustomAppBar() {
        TopAppBar(
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        modifier = Modifier
                            .size(48.dp)
                            .fillMaxWidth()
                            .padding(end = 8.dp),
                        imageVector = Icons.Filled.AccountCircle,
                        contentDescription = "profile picture"
                    )
                    Text("Pontos Livelo Amazon")
                }
            },
            navigationIcon = {
//                IconButton({}) {
//                    Icon(
//                        imageVector = Icons.Filled.ArrowBack,
//                        contentDescription = "menu items"
//                    )
//                }
            },
            actions = {
//                IconButton(onClick = {}) {
//                    Icon(
//                        imageVector = Icons.Filled.AddCircle,
//                        contentDescription = "settingd"
//                    )
//                }
//                IconButton(onClick = {}) {
//                    Icon(
//                        imageVector = Icons.Filled.Call,
//                        contentDescription = "phone call"
//                    )
//                }
//                IconButton(onClick = {}) {
//                    Icon(
//                        imageVector = Icons.Filled.MoreVert,
//                        contentDescription = "more options"
//                    )
//                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(MaterialTheme.colorScheme.primaryContainer)
        )
    }


    @Preview
    @Composable
    fun FistScreenPreview() {
        FirstScreen()
    }
}