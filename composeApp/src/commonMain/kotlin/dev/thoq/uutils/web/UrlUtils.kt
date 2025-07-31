package dev.thoq.uutils.web

object UrlUtils {
    fun getNameFromUrl(url: String): String? {
        val regex = Regex("""(?:https?://)?(?:www\.)?([a-zA-Z0-9-]+)\.[a-zA-Z]{2,}(?:/.*)?""")
        val matchResult = regex.find(url)
        return matchResult?.groups?.get(1)?.value
    }
}