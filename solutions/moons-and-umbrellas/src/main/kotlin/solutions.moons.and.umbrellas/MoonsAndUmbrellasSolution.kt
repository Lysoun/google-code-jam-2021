fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        // Ignore first line of case
        readLine()
        val inputLine = readLine()!!.split(" ")
        println("Case #$i: ${computeMuralCost(ProblemInput(inputLine[0].toInt(), inputLine[1].toInt(), inputLine[2]))}}")
    }
}

fun computeMuralCost(problemInput: ProblemInput): Int {
    return 0
}

data class ProblemInput(val cjCost: Int, val jcCost: Int, val mural: String)