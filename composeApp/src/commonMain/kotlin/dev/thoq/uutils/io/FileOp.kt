package dev.thoq.uutils.io

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class FileOp() {
    @Suppress("unused")
    fun writeText(path: String, text: String)
    fun getHome(): String
}