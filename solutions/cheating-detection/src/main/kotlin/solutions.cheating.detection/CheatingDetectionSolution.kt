fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    // Ignore required percentage to success
    readLine()

    for (i in 1..casesNumber) {
        println("Case #$i: ${findCheater(List(100) { 0 }.map { readLine()!! })}")
    }
}

fun findCheater(playersAnswers: List<String>): Int {
    return 0
}