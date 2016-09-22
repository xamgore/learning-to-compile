package Step1.Task3

import Step1.Util.Scan
import Step1.Util.doJob


// Целое со знаком, начинающееся не с цифры 0. (1 балл)
// (+|-|)ц+

fun main(args: Array<String>) {
    doJob(step = 1, task = 3) { Scan(it).andTokenizeInteger() }
}


fun Scan.andTokenizeInteger(): CharSequence {
    val buf = StringBuilder()
    var ch  = peekChar()

    if (ch == '+' || ch == '-') {
        buf.append(ch)
        ch = nextChar()
    }

    if (ch == '0' || !ch.isDigit()) return ""

    while (ch.isDigit()) {
        buf.append(ch)
        ch = nextChar()
    }

    return if (ch == EOL) { buf } else { "" }
}
