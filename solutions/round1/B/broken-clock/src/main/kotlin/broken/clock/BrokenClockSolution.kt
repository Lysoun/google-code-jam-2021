import java.math.BigInteger

fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        // Ignore list size
        readLine()
//        println("Case #$i: ${countDigitsRequiredForAppendSort(readLine()!!.split(" ").map { it.toBigInteger() })}")
    }
}

fun computeTime(input: List<BigInteger>): Time {
    return Time(0, 0, 0, 0)
}

data class Time(val hour: Int, val minutes: Int, val seconds: Int, val nanoseconds: Int)