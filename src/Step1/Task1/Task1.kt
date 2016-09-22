package Step1.Task1

import Step1.Util.Scan
import Step1.Util.doJob


// Реализовать в программе семантические действия по накоплению
// в строке распознанного целого числа и преобразованию его в целое
// в конце разбора (при встрече завершающего символа).
// Семантические действия следует добавлять перед каждым
// вызовом NextCh кроме первого. (1 балл)

fun main(args: Array<String>) {
    doJob(step = 1, task = 1) { Scan(it).andTokenizeInteger() }
}


fun Scan.andTokenizeInteger(): CharSequence {
    val buf = StringBuilder()
    var ch  = peekChar()

    while (ch.isDigit()) {
        buf.append(ch)
        ch = nextChar()
    }

    return if (ch == EOL) { buf } else { "" }
}
