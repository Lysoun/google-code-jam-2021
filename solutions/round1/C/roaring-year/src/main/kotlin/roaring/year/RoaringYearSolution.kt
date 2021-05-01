import java.math.BigInteger

fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        println("Case #$i: ${findNextRoaringYear(BigInteger(readLine()!!))}")
    }
}

fun findNextRoaringYear(currentYear: java.math.BigInteger): java.math.BigInteger {
    return BigInteger.ZERO
}