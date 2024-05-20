package br.dev.gustavovilela.firstprojectcompose.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleCoroutineScope
import br.dev.gustavovilela.firstprojectcompose.R
import kotlinx.coroutines.CompletableDeferred

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun HomeScreen(
    title: String,
    textButton: String,
    contentText: String,
    lifecycle: LifecycleCoroutineScope?,
    context: Context
) {
    var texto by remember { mutableStateOf("Aguardando busca...") }
    var isLoading by remember { mutableStateOf(false) }
    var isLoaded by remember { mutableStateOf(false) }

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
            WebView(it).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                settings.allowFileAccess = true
                settings.allowContentAccess = true
                webViewClient = object : WebViewClient() {
                    override fun onPageFinished(view: WebView, url: String) {
                        super.onPageFinished(view, url)
                        extractData(elementId = "parity-card__parity-bau", view)
                    }
                }
                loadUrl("https://www.google.com")
            }
        }, update = { webView ->
            // O WebView será atualizado se necessário
            webView.loadUrl("https://www.google.com")
        }
    )

/*
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = title, style = TextStyle(fontSize = 24.sp))

        Button(onClick = {
            val context = WeakReference<Context>(context)
            val webContentParserService = WebContentParserService(
                context = context.get()!!,
                url = "https://www.livelo.com.br/ganhe-pontos-compre-pontue-partner/amazon/AMZ",
                elementId = "parity-card__parity-bau"
            )
            isLoading = true
            texto = "Carregando..."
            lifecycle?.launch {
                val result = webContentParserService.loadDataFromUrl()
                texto = result ?: "Falha ao obter o texto"
                isLoading = false
                isLoaded = true
            }
        }) {
            Text(textButton)
        }

        if (!isLoading) {
            Text(
                text = texto,
                style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold)
            )
        } else {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
    }
    */
}

private fun extractData(elementId: String, view: WebView, data: CompletableDeferred<String?>? = null) {
    view.evaluateJavascript("(function() { return document.getElementById('$elementId').innerText; })();") { value ->
        data?.complete(value)
        Log.i("TEST::", value)
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val currentContext = LocalContext.current
    HomeScreen(
        title = currentContext.getString(R.string.title),
        textButton = currentContext.getString(R.string.textButton),
        contentText = "10 pontos por Real",
        lifecycle = null,
        context = LocalContext.current
    )
}