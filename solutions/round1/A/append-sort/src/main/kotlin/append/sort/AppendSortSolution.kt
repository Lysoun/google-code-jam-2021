fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        // Ignore list size
        readLine()
        println("Case #$i: ${countDigitsRequiredForAppendSort(readLine()!!.split(" ").map { it.toInt() })}")
    }
}

fun countDigitsRequiredForAppendSort(list: List<Int>): Int {
    val sortedList = list.toMutableList()
    var digitsRequired = 0
    var digitToAdd = 0
    var last = sortedList[0]

    for(i in 1 until list.size) {
        if (sortedList[i] == last) {
            ++digitToAdd
        } else {
            last = sortedList[i]
        }

        while (sortedList[i] <= sortedList[i - 1]) {
            sortedList[i] = sortedList[i] * 10 + digitToAdd
            ++digitsRequired
        }

        if (digitToAdd > 9) {
            digitToAdd = 0
        }
    }

    return digitsRequired
}