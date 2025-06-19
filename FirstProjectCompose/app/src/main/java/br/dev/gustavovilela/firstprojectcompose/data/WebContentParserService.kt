package br.dev.gustavovilela.firstprojectcompose.data

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.coroutines.CompletableDeferred

class WebContentParserService(
    private val context: Context,
    private val elementId: String,
    private val url: String
) {
    val data = CompletableDeferred<String?>()
    @SuppressLint("SetJavaScriptEnabled")
    private val webView: WebView = WebView(context).apply {
        webViewClient = WebViewClient()  // Definir um cliente básico
        settings.javaScriptEnabled = true  // Habilitar JavaScript
        webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                super.onPageFinished(view, url)
                extractData(elementId, view, data)
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                super.onReceivedError(view, request, error)
            }
        }
    }

    fun getWebView(): WebView = webView

    suspend fun loadDataFromUrl(): String? {
//        val data = CompletableDeferred<String?>()
        webView.clearCache(true)
        webView.loadUrl(url)
//        withContext(Dispatchers.Main) {
//
//        }

        return data.await()  // Retorna o resultado quando estiver pronto
    }

    private fun logHtmlContent() {
        webView.evaluateJavascript("(function() { return document.documentElement.outerHTML; })();") { html ->
            Log.i("WebContentService", "HTML Content: $html")
        }
    }

    private fun extractData(elementId: String, view: WebView, data: CompletableDeferred<String?>) {
        view.evaluateJavascript("(function() { return document.getElementById('$elementId').innerText; })();") { value ->
            data.complete(value)
        }
    }
}
