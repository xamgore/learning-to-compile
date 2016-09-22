package Step1.Task2

import Step1.Util.doJob
import Step1.Util.Scan


// Идентификатор. (1 балл)
// б(б|ц)*

fun main(args: Array<String>) {
    doJob(step = 1, task = 2) { Scan(it).andTokenizeId() }
}

fun Scan.andTokenizeId(): CharSequence {
    val buf = StringBuilder()
    var ch = peekChar()

    if (!ch.isLetter())
        return ""

    while (ch.isLetterOrDigit()) {
        buf.append(ch)
        ch = nextChar()
    }

    return if (ch == EOL) { buf } else ""
}