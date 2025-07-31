package dev.thoq.uutils.types

object TypeUtils {
    fun String.capitalizeFirst(): String = this.replaceFirstChar { firstChar -> firstChar.uppercase() }
}
