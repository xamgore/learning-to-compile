package Side

fun main(args: Array<String>) {
    val text = """
        |1234.4
        |kek
    """.trimMargin()

    val l = Lexer(text)

    println(l.tokenize().joinToString("\n"))
}

