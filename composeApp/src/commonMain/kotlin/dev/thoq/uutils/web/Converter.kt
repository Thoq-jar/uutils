package dev.thoq.uutils.web

import androidx.compose.material3.*
import androidx.compose.runtime.*
import dev.thoq.uutils.io.FileOp
import dev.thoq.uutils.types.TypeUtils.capitalizeFirst
import org.jetbrains.compose.ui.tooling.preview.Preview

fun generateDesktopCode(name: String, url: String): String {
    val template = """
        #!/usr/bin/env python3
        import gi;gi.require_version("Gtk", "3.0");gi.require_version("WebKit2", "4.1");from gi.repository import Gtk, WebKit2
        class Browser:
            def __init__(self):
                self.window = Gtk.Window(title="%%{NAME}%%")
                self.window.set_default_size(800, 600)
                self.webview = WebKit2.WebView()
                self.webview.load_uri("%%{SITE}%%")
                scrolled_window = Gtk.ScrolledWindow()
                scrolled_window.add(self.webview)
                self.window.add(scrolled_window)
                self.window.connect("destroy", Gtk.main_quit)
                self.window.show_all()
        Browser()
        Gtk.main()
    """.trimIndent()

    return template.replace("%%{NAME}%%", name).replace("%%{SITE}%%", url)
}

@Composable
@Preview
fun Converter() {
    var showDialog by remember { mutableStateOf(true) }
    var inputText by remember { mutableStateOf("") }

    MaterialTheme {
        if(showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Enter the URL") },
                text = {
                    TextField(
                        value = inputText,
                        onValueChange = { inputText = it },
                        label = { Text("URL") }
                    )
                },
                confirmButton = {
                    TextButton(onClick = {
                        val fw = FileOp()
                        val homeDir = fw.getHome()
                        val name = UrlUtils.getNameFromUrl(inputText)
                            ?.capitalizeFirst() ?: inputText
                            .replace("http://", "")
                            .replace("https://", "")
                            .capitalizeFirst()
                        val path = "$homeDir/.local/bin/$name"
                        val program = generateDesktopCode(name, inputText)

                        println("Writing to: $path")
                        fw.writeText(path, program)

                        showDialog = false
                    }) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}