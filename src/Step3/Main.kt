package Step3

val path = "/home/ksa/IdeaProjects/learning-to-compile/src/Step3/program.txt"

fun main(args: Array<String>) {
    SimpleLangParser(SimpleLangLexer(path)).program()
}