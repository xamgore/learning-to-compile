package Step3

import java.nio.file.Files
import java.nio.file.Paths


class SimpleLangParser(val lex: SimpleLangLexer) {

    var current: Token = lex.nextLexem()


    fun program() {
        block()
        matchOrDie(Type.EOF, "End of file expected")
    }

    fun block() {
        matchOrDie(Type.BEGIN, "Begin keyword expected")
        statementList()
        matchOrDie(Type.END, "End keyword expected")
    }

    fun statementList() {
        statement()
        while (skip(Type.SEMICOLON))
            statement()
    }

    fun statement() {
        when (current.type) {
            Type.BEGIN ->
                block()
            Type.CYCLE ->
                cycle()
            Type.ID ->
                assign()
            else -> syntaxError("Operator expected")
        }
    }

    fun cycle() {
        matchOrDie(Type.CYCLE)
        expression()
        statement()
    }

    fun assign() {
        matchOrDie(Type.ID)
        matchOrDie(Type.ASSIGN, "Expected :=")
        expression()
    }

    fun expression() {
        if (skip(Type.ID) || skip(Type.INT))
            return

        syntaxError("Expression expected")
    }


    fun skip(type: Type): Boolean {
        if (current.type == type) {
            current = lex.nextLexem()
            return true
        }

        return false
    }

    fun matchOrDie(type: Type, errMsg: String = "ERROR"): Boolean {
        if (current.type != type) {
            syntaxError(errMsg)
            return false
        }

        current = lex.nextLexem()
        return true
    }

    fun syntaxError(errMsg: String = "") {
        val (row, col) = lex.tokenPos

        val count = if (col <= 1) 0 else col - 1
        val spaces = StringBuilder("~").repeat(count).toString()
        val source = Files.lines(Paths.get(lex.fileName))
                .skip(row).limit(1).toArray().first()

        println("\n$row:$col $errMsg\n$source\n$spaces^\n")
        System.exit(0)
    }

}