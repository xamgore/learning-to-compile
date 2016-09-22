package Step1.Task9

import Step1.Util.Scan
import Step1.Util.doJob
import Step1.Util.plusAssign


// Лексема вида 'строка', внутри апострофов отсутствует символ ' (1 балл).

fun main(args: Array<String>) {
    doJob(step = 1, task = 9) { Scan(it).tokenizeString() }
}


fun Scan.tokenizeString(): CharSequence {
    val buf = StringBuilder()
    var ch = peekChar()

    if (ch != '\'') return ""
    ch = nextChar()

    while (ch != '\'') {
        if (ch == EOL) return ""

        buf += ch
        ch = nextChar()
    }

    ch = nextChar()

    return if (ch == EOL) { buf } else { "" }
}