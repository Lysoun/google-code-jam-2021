fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        val lineInput = readLine()!!.split(" ")

        println("Case #$i: ${findListMatchingConstraints(ProblemInput(lineInput[0].toInt(), lineInput[1].toInt()))}")
    }
}

const val IMPOSSIBLE = "IMPOSSIBLE"

fun buildSmallestNumberPositionPerIteration(problemInput: ProblemInput): List<Int> {
    val smallestNumberPositionPerIteration = MutableList(problemInput.listSize - 1) { 1 }

    var cost = problemInput.cost - problemInput.listSize + 1
    var i = problemInput.listSize - 1

    while(cost > 0 && i > 0) {
        if (cost >= i) {
            cost -= i
            smallestNumberPositionPerIteration[problemInput.listSize - i - 1] = i + 1
        }
        --i
    }

    return smallestNumberPositionPerIteration
}

fun reverseSubList(list: List<Int>, startingIndex: Int, endingIndex: Int): List<Int> {
    return list.subList(0, startingIndex) +
            list.subList(startingIndex, endingIndex).reversed() +
            list.subList(endingIndex, list.size)
}

fun buildListMatchingConstraints(problemInput: ProblemInput): List<Int> {
    val smallestNumberPositionPerIteration = buildSmallestNumberPositionPerIteration(problemInput).reversed()
    var listMatchingConstraints = List(problemInput.listSize) { it + 1 }

    var index = problemInput.listSize - 2

    for(i in smallestNumberPositionPerIteration) {
        if (i > 1) {
            listMatchingConstraints = reverseSubList(listMatchingConstraints, index, index + i)
        }
        --index
    }

    return listMatchingConstraints
}

fun findListMatchingConstraints(problemInput: ProblemInput): String {
    // Having a cost inferior to listSize - 1 is impossible because there will be listSize - 1 iterations
    // and each iteration has a minimum cost of 1
    if (problemInput.cost < problemInput.listSize - 1) {
        return IMPOSSIBLE
    }

    // Having a cost superior to listSize(listSize + 1)/2 - 1 is impossible because
    // there will be listSize - 1 iterations and each iteration i has a maximum cost of listSize - i
    if (problemInput.cost > problemInput.listSize * (problemInput.listSize + 1) / 2 - 1) {
        return IMPOSSIBLE
    }

    return buildListMatchingConstraints(problemInput).joinToString(" ")
}

data class ProblemInput(val listSize: Int, val cost: Int)