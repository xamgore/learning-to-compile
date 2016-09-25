package Step2

import Step1.Util.plusAssign
import java.nio.file.Files
import java.nio.file.Paths


enum class Type {
    EOF, ID, INT, COLON, SEMICOLON, ASSIGN, BEGIN, END, CYCLE
}

data class Token(val type: Type, val value: Any = "") {
    override fun toString(): String = "$type $value"
}


class SimpleLangLexer(val fileName: String) {

    val input = Files.readAllLines(Paths.get(fileName)).joinToString("\n")

    val keywords = mapOf(
            Pair("begin", Token(Type.BEGIN)),
            Pair("end", Token(Type.END)),
            Pair("cycle", Token(Type.CYCLE))
    )

    var row = 0L
    var col = 0
    var pos = 0
    var ch: Char = ' '
    val EOF = '\u0000'


    fun lexError(message: String = "") {
        val count = if (col <= 1) 0 else col - 2
        val spaces = StringBuilder("~").repeat(count).toString()
        val source = Files.lines(Paths.get(fileName)).skip(row).limit(1).toArray().first()

        println("\n$row:$col $message\n$source\n$spaces^\n")
        System.exit(0)
    }

    fun nextChar(): Char {
        if (pos >= input.length) {
            ch = EOF
            return ch
        }

        ch = input[pos++]

        if (ch == '\n') {
            col = 1; row++
        } else {
            col++
        }

        return ch
    }

    fun passSpaces() {
        while (ch.isWhitespace())
            nextChar()
    }

    fun nextLexem(): Token {
        passSpaces()

        when (ch) {
            ';' -> {
                ch = nextChar()
                return Token(Type.SEMICOLON)
            }

            ':' -> {
                ch = nextChar()
                if (ch != '=')
                    lexError("Expected assignment (=)")

                ch = nextChar()
                return Token(Type.ASSIGN)
            }

            in 'a'..'z' -> {
                val seq = StringBuilder()

                while (ch in 'a'..'z' || ch in '0'..'9') {
                    seq += ch; ch = nextChar()
                }

                val word = seq.toString()
                return keywords[word] ?: Token(Type.ID, word)
            }

            in '0'..'9' -> {
                val seq = StringBuilder()

                while (ch.isDigit()) {
                    seq += ch; ch = nextChar()
                }

                return Token(Type.INT, seq.toString().toInt())
            }

            EOF -> {
                return Token(Type.EOF)
            }

            else -> {
                lexError("Unexpected symbol: $ch")
                return Token(Type.EOF)
            }
        }
    }
}

