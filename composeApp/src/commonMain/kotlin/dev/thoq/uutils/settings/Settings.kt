package dev.thoq.uutils.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object Settings {
    var webAppDesktop: Boolean by mutableStateOf(false)
}