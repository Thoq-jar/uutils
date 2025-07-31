package dev.thoq.uutils.io

import java.io.File

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class FileOp {
    actual fun writeText(path: String, text: String) {
        val file = File(path)

        if(!file.exists())
            file.createNewFile()

        file.writeText(text)
        file.setExecutable(true)
    }

    actual fun getHome(): String {
        return System.getProperty("user.home")
    }
}
