package Step1.Task6

import Step1.Util.*


// Список цифр, разделенных одним или несколькими пробелами.
// В качестве семантического действия должно быть накопление списка
// цифр в списке и вывод этого списка в конце программы (1 балл).

fun main(args: Array<String>) {
    doJob(step = 1, task = 6) { Scan(it).andTokenizeIntegers() }
}


fun Scan.andTokenizeIntegers(): CharSequence {
    val list = mutableListOf<Int>()
    val buf = StringBuilder()
    var ch  = peekChar()

    do {
        while (ch.isDigit()) {
            buf.append(ch)
            ch = nextChar()
        }

        if (buf.length > 0) {
            list += buf.toString().toInt()
            buf.setLength(0)
        }

        while (ch == ' ')
            ch = nextChar()

    } while (ch.isDigit())


    return if (ch == EOL) { list.joinToString(" ") } else { "" }
}
