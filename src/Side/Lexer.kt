package Side

enum class Type {
    LITERAL_INT,
    IDENTIFIER,

    EOL
}

data class Token(val type: Type, val value: Any = "") {
    override fun toString(): String = "$type: $value"
}

class Lexer(val input: String) {
    var cursor: Int = 0
    val list = mutableListOf<Token>()


    fun tokenize(): List<Token> {
        while (cursor < input.length) {
            val cur = peekChar()

            when {
                cur.isDigit() ->
                    tokenizeNumber()
                cur.isLetter() ->
                    tokenizeIdentifier()
                else ->
                    nextChar()  // whitespace
            }
        }

        return list
    }

    fun tokenizeNumber() {
        val buf = StringBuilder()
        var ch = peekChar()

        do {
            if (!ch.isDigit()) {
                if ((ch == '.') && (ch in buf))
                    throw Exception("Invalid float format, unexpected dot")
                break
            }

            buf.append(ch)
            ch = nextChar()
        } while (true)


//        addToken(Type.LITERAL_INT, s.toString())
    }

    fun tokenizeIdentifier() {
        val buf = StringBuilder()
        var ch = peekChar()

        while (ch.isLetterOrDigit()) {
            buf.append(ch)
            ch = nextChar()
        }

        addToken(Type.IDENTIFIER, buf.toString())
    }


    private fun peekChar(position: Int = 0): Char {
        val idx = cursor + position

        return if (idx >= input.length) '\u0000' else input[idx]
    }

    private fun nextChar(): Char {
        cursor++
        return peekChar()
    }

    private fun addToken(type: Type, value: Any = "") {
        list += Token(type, value)
    }

//    private nextChar(): Char
}