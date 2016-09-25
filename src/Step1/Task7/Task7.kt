package Step1.Task7

import Step1.Util.*


// Лексема вида aa12c23dd1, в которой чередуются группы букв и цифр,
// в каждой группе не более 2 элементов. В качестве семантического
// действия необходимо накопить данную лексему в виде строки (1 балл).

fun main(args: Array<String>) {
    doJob(step = 1, task = 7) { Scan(it).andTokenizeGroups() }
}

fun Scan.andTokenizeGroups(): CharSequence {
    val buf = StringBuilder()
    var ch = peekChar()

    if (!ch.isLetter()) return ""
    buf += ch; ch = nextChar()

    if (ch.isLetter()) {
        buf += ch; ch = nextChar()
    }

    while (ch.isDigit()) {
        if (!ch.isDigit()) break
        buf += ch; ch = nextChar()

        if (ch.isDigit()) {
            buf += ch; ch = nextChar()
        }

        if (!ch.isLetter()) break
        buf += ch; ch = nextChar()

        if (ch.isLetter()) {
            buf += ch; ch = nextChar()
        }
    }

    return if (ch == EOL) { buf } else { "" }
}