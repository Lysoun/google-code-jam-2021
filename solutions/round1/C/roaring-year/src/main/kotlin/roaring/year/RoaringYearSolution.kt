import java.math.BigInteger

fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        println("Case #$i: ${findNextRoaringYear(readLine()!!)}")
    }
}

fun findNextRoaringYear(currentYear: String): BigInteger {
    return BigInteger.ZERO
}