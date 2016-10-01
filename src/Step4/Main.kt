package Step4

val path = "/home/ksa/IdeaProjects/learning-to-compile/src/Step4/program.txt"

fun main(args: Array<String>) {
    SimpleLangParser(SimpleLangLexer(path)).program()
}