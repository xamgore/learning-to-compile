package Step2

val path = "/home/ksa/IdeaProjects/learning-to-compile/src/Step2/a.txt"


fun main(args: Array<String>) {
    val lexer = SimpleLangLexer(path)

    do {
        val token = lexer.nextLexem()
        println(token)
    } while (token.type != Type.EOF)
}