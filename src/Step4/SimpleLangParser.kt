package Step4

import java.nio.file.Files
import java.nio.file.Paths


class SimpleLangParser(val lex: SimpleLangLexer) {

    var current: Token = lex.nextLexem()


    fun program() {
        block()
        matchOrDie(Type.EOF, "End of file expected")
    }

    fun block() {
        matchOrDie(Type.BEGIN)
        statementList()
        matchOrDie(Type.END)
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
            Type.WHILE ->
                `while`()
            Type.FOR ->
                `for`()
            Type.IF ->
                `if`()
            else -> syntaxError("Operator expected")
        }
    }

    fun `if`() {
        matchOrDie(Type.IF)
        expression()
        matchOrDie(Type.THEN)
        statement()

        if (skip(Type.ELSE))
            statement()
    }

    fun `while`() {
        matchOrDie(Type.WHILE)
        expression()
        matchOrDie(Type.DO)
        statement()
    }

    fun `for`() {
        matchOrDie(Type.FOR)
        assign()
        matchOrDie(Type.TO)
        expression()
        matchOrDie(Type.DO)
        statement()
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
        binaryAdd()
    }

    fun binaryAdd() {
        binaryMult()
        while (skip(Type.PLUS) || skip(Type.MINUS))
            binaryMult()
    }

    fun binaryMult() {
        operand()
        while (skip(Type.PRODUCT) || skip(Type.DIVISION))
            operand()
    }

    fun operand() {
        if (skip(Type.LBRACKET)) {
            expression()
            matchOrDie(Type.RBRACKET)
        } else {
            if (skip(Type.ID) || skip(Type.INT))
                return
        }

        syntaxError("Expression expected")
    }


    fun skip(type: Type): Boolean {
        if (current.type == type) {
            current = lex.nextLexem()
            return true
        }

        return false
    }

    fun matchOrDie(type: Type, errMsg: String = "$type keyword expected"): Boolean {
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