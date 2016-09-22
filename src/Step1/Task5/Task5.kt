package Step1.Task5

import Step1.Util.Scan
import Step1.Util.doJob
import Step1.Util.plusAssign


// Список букв, разделенных символом , или ; В качестве семантического действия должно
// быть накопление списка букв в списке и вывод этого списка в конце программы.(1 балл)

fun main(args: Array<String>) {
    doJob(step = 1, task = 5) { Scan(it).andTokenizeLetters() }
}


fun Scan.andTokenizeLetters(): CharSequence {
    val buf = StringBuilder()
    var ch = peekChar()

    if (!ch.isLetter())
        return ""

    while (true) {
        if (!ch.isLetter()) break
        buf += ch; ch = nextChar()

        if (ch != ';' && ch != ',') break
        buf += ch; ch = nextChar()
    }

    return if (ch == EOL) { buf } else { "" }
}
