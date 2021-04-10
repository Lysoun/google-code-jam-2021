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

    for(i in 1 until list.size) {
        while(sortedList[i] <= sortedList[i - 1]) {
            if(sortedList[i] == sortedList[i - 1])
            sortedList[i] = sortedList[i] * 10 + 9
            ++digitsRequired
        }
    }

    return digitsRequired
}