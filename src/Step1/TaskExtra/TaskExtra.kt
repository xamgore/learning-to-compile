package Step1.TaskExtra

import Step1.Util.*


// Лексема вида Id1.Id2.Id3 (количество идентификаторов может быть произвольным).
// Идентификатор: б(б|ц)*

fun main(args: Array<String>) {
    doJob(step = 1, task = "Extra") { Scan(it).andTokenizeSeq() }
}

fun Scan.andTokenizeSeq(): CharSequence {
    val buf = StringBuilder()
    var ch = peekChar()

    while (ch.isLetter()) {
        while (ch.isLetterOrDigit()) {
            buf += ch; ch = nextChar()
        }

        if (ch == '.') {
            buf += ch; ch = nextChar()
        }
    }

    if (buf.isNotEmpty() && buf[buf.length - 1] == '.')
        return ""

    return if (ch == EOL) buf else ""
}