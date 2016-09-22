package Step1.Task4

import Step1.Util.Scan
import Step1.Util.doJob
import Step1.Util.plusAssign


// Чередующиеся буквы и цифры, начинающиеся с буквы.(1 балл)
// (a0)+ a?

fun main(args: Array<String>) {
    doJob(step = 1, task = 4) { Scan(it).andTokenizeABAB() }
}


fun Scan.andTokenizeABAB(): CharSequence {
    val buf = StringBuilder()
    var ch = peekChar()


    if (!ch.isLetter()) return ""
    buf += ch; ch = nextChar()

    if (!ch.isDigit()) return ""
    buf += ch; ch = nextChar()


    while (true) {
        if (!ch.isLetter()) break
        buf += ch; ch = nextChar()

        if (!ch.isDigit()) break
        buf += ch; ch = nextChar()
    }


    return if (ch == EOL) { buf } else { "" }
}
