data class ProblemInput(val metalTypesNumber: Int, val spells: List<Int>, val unitsRequired: List<Int>)

fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        val line1 = readLine()!!.split(" ").map { it.toInt() }
        val line2 = readLine()!!.split(" ").map { it.toInt() }
        println(
            "Case #$i: ${
                findSmallestMetalToProduceAllRequiredUnits(
                    ProblemInput(
                        line1[0],
                        line1.subList(1, 3),
                        line2
                    )
                )
            }"
        )
    }
}

const val IMPOSSIBLE = "IMPOSSIBLE"

fun findSmallestMetalToProduceAllRequiredUnits(problemInput: ProblemInput): String {
    if(problemInput.spells.all { it % 2 == 0 } &&
        problemInput.unitsRequired.filterIndexed {index: Int, i: Int -> index % 2 == 1 && i > 0 }.isNotEmpty()) {
        return IMPOSSIBLE
    }

    return ""
}