fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        // Ignore first line of case
        readLine()

        println("Case #$i: }")
    }
}

fun computeMuralCost(problemInput: ProblemInput): Int {
    return 0
}

data class ProblemInput(val cjCost: Int, val jcCost: Int, val mural: String)