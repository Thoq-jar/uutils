package dev.thoq.uutils

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform