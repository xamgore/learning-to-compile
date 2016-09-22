package Step1.Util

class Scan(val input: String) {
    val EOL = '\u0000'
    var cursor = 0

    fun peekChar(position: Int = 0): Char {
        val idx = cursor + position

        return if (idx >= input.length) EOL else input[idx]
    }

    fun nextChar(): Char {
        cursor++
        return peekChar()
    }
}