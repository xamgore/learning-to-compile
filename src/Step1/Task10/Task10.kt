package Step1.Task10

import Step1.Util.Scan
import Step1.Util.doJob
import Step1.Util.plusAssign


// Лексема вида 'строка', внутри апострофов отсутствует символ ' (1 балл).

fun main(args: Array<String>) {
    doJob(step = 1, task = 10) { Scan(it).tokenizeComment() }
}


fun Scan.tokenizeComment(): CharSequence {
    val buf = StringBuilder()
    var ch = peekChar()

    if (ch != '/' || nextChar() != '*') return ""
    ch = nextChar()

    while (true) {
        if (ch == '*') {
            ch = nextChar()
            if (ch == '/') break
            buf += '*'
            continue
        }

        if (ch == EOL) return ""

        buf += ch
        ch = nextChar()
    }

    ch = nextChar()

    return if (ch == EOL) { buf } else { "" }
}