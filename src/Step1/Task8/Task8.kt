package Step1.Task8

import Step1.Util.Scan
import Step1.Util.doJob
import Step1.Util.plusAssign


// Реализовать в программе семантические действия по накоплению
// в строке распознанного целого числа и преобразованию его в целое
// в конце разбора (при встрече завершающего символа).
// Семантические действия следует добавлять перед каждым
// вызовом NextCh кроме первого. (1 балл)

fun main(args: Array<String>) {
    doJob(step = 1, task = 8) { Scan(it).tokenizeFloat() }
}


fun Scan.tokenizeFloat(): CharSequence {
    val buf = StringBuilder()
    var ch = peekChar()

    loop@ do {
        when {
            ch.isDigit() -> {
                buf += ch
                ch = nextChar()
            }

            ch == '.' -> {
                if (ch in buf) { return "" }

                buf += ch
                ch = nextChar()
            }

            else -> break@loop
        }
    } while (true)

    if ('.' !in buf)
        return ""

    return if (ch == EOL) { buf } else { "" }
}