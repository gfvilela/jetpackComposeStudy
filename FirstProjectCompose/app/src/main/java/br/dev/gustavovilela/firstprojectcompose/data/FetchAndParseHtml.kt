package br.dev.gustavovilela.firstprojectcompose.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup

class FetchAndParseHtml {
    suspend fun fetchAndParseHtml(url: String, elementId: String): String? = withContext(Dispatchers.IO) {
        try {
            // Realiza a conexão e obtém o documento
            val doc = Jsoup.connect(url).get()

            // Retorna o texto do elemento com o ID especificado
            doc.getElementById(elementId)?.text()
        } catch (e: Exception) {
            // Loga exceções e retorna null em caso de erro
            e.printStackTrace()
            null
        }
    }
}
