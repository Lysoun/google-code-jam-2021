class Solution {
    companion object {
        fun main(args: Array<String>) {
            val casesNumber = readLine()!!.toInt()

            for(i in 1..casesNumber) {
                // Ignore first line of case
                readLine()

                println("Case #$i: ${computeReverseCostToSortList(readLine()!!.split(" ").map { it.toInt() })}")
            }
        }
    }
}

fun computeReverseCostToSortList(numbers: List<Int>): Int {
    return 0
}