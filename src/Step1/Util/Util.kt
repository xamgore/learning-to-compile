package Step1.Util

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

fun doJob(step: Int, task: Int, scanner: (String) -> (CharSequence)) {
    val parsed = Files
            .lines(path("Step$step/Task$task/in.txt"))
            .map(scanner)
            .toArray()
            .joinToString("\n")

    Files.write(path("Step$step/Task$task/out.txt"), parsed.toByteArray())
}

fun path(file: String): Path =
        Paths.get("/home/ksa/IdeaProjects/learning-to-compile/src/$file")

operator fun StringBuilder.plusAssign(b: Any) {
    this.append(b)
}