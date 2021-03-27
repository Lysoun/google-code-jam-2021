interface Judge {
    fun askJudge(numbers: List<Int>): Int
}

class RealJudge: Judge {
    override fun askJudge(numbers: List<Int>): Int {
        println(numbers.joinToString(" "))
        return readLine()!!.toInt()
    }
}

fun main(args: Array<String>) {
    val input = readLine()!!.split(" ").map { it.toInt() }
    val casesNumber = input[0]
    val listSize = input[1]

    for (i in 1..casesNumber) {
        sortList(listSize, RealJudge())
    }
}

fun sortList(listSize: Int, judge: Judge): List<Int> {
    var numbersToSort = MutableList(listSize) { it + 1 }

    var triplet = numbersToSort.subList(0, 3)
    var median = judge.askJudge(triplet)

    val tripletWithoutMedian = triplet.filter { it != median }
    var sortedNumbers = listOf(tripletWithoutMedian[0], median, tripletWithoutMedian[1])
    numbersToSort = numbersToSort.filter { !triplet.contains(it) }.toMutableList()
    var numberToSort: Int? = null
    var left = 0
    var right = sortedNumbers.size - 1

    while(sortedNumbers.size < listSize) {
        if(numberToSort == null) {
            numberToSort = numbersToSort.removeFirst()
            left = 0
            right = sortedNumbers.size - 1
        }

        val middle = (left + right) / 2
        triplet = (sortedNumbers.subList(middle, middle + 2) + listOf(numberToSort)).toMutableList()
        median = judge.askJudge(triplet)

        if(middle == 0 && median == sortedNumbers[0]) {
            sortedNumbers = listOf(numberToSort) + sortedNumbers
            numberToSort = null
        } else {
            if(middle == (sortedNumbers.size - 2) && median == sortedNumbers[sortedNumbers.size - 1]) {
                sortedNumbers = sortedNumbers + listOf(numberToSort)
                numberToSort = null
            } else {
                if(median == numberToSort) {
                    sortedNumbers = (sortedNumbers.subList(0, middle + 1) +
                            listOf(numberToSort) +
                            sortedNumbers.subList(middle + 1, sortedNumbers.size)).toMutableList()
                    numberToSort = null
                }
            }
        }

        if(median == triplet[1]) {
            left = middle + 1
        } else {
            right = middle - 1
        }
    }

    judge.askJudge(sortedNumbers)
    return sortedNumbers
}