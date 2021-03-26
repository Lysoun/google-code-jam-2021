fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        // Ignore first line of case
        readLine()

        println("Case #$i: ${computeReverseCostToSortList(readLine()!!.split(" ").map { it.toInt() })}")
    }
}

fun computeReverseCostToSortList(numbers: List<Int>): Int {
    var totalCost = 0
    var currentList = numbers
    for (i in 0 until (numbers.size - 1) ) {
        val result = reverseAndComputeCost(currentList, i)
        currentList = result.first
        totalCost += result.second
    }
    return totalCost
}

fun reverseAndComputeCost(numbers: List<Int>, startingIndex: Int): Pair<List<Int>, Int> {
    val smallestNumberPosition = numbers.subList(startingIndex, numbers.size)
        .mapIndexed { index, i -> index to i }
        .minByOrNull { it.second }!!
        .first

    if (smallestNumberPosition == 0) {
        return numbers to 1
    }

    return (
            numbers.subList(0, startingIndex) +
                    numbers.subList(startingIndex, smallestNumberPosition + startingIndex + 1).reversed() +
                    numbers.subList(smallestNumberPosition + 1, numbers.size)
            ) to (smallestNumberPosition + 1)
}