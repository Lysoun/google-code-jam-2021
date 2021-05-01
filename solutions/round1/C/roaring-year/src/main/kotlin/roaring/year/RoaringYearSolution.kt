import java.math.BigInteger

fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        println("Case #$i: ${findNextRoaringYear(readLine()!!)}")
    }
}

fun findNextRoaringYear(currentYear: String): BigInteger {
    val roaringYears = MutableList<BigInteger>(0) { BigInteger.ZERO }
    val currentYearBigInt = BigInteger(currentYear)

    var firstTry = true
    for (i in currentYear.indices) {
        var foundMinimum = false
        var k = BigInteger.ZERO
        while (!foundMinimum) {
            val firstNumber = if (firstTry) {
                BigInteger.ONE
            } else {
                (BigInteger(currentYear.substring(0, i + 1)) + k)
            }

            var j = 1L
            var year = firstNumber.toString()
            if (year.length <= (currentYear.length + 1) / 2 || roaringYears.isEmpty()) {
                var yearBigInt: BigInteger
                var yearTooBig = false

                do {
                    val newYear = (year + (firstNumber + BigInteger.valueOf(j)).toString())
                    year = newYear
                    yearBigInt = BigInteger(newYear)

                    if (year.length > currentYear.length + 2) {
                        yearTooBig = true
                    } else {
                        ++j
                    }
                } while (yearBigInt <= currentYearBigInt && !yearTooBig)

                val currentMin = roaringYears.minOrNull()
                if (yearBigInt > currentYearBigInt && (currentMin == null || yearBigInt < currentMin) && year.length > 1) {
                    if (k >= BigInteger.ONE && !firstTry) {
                        foundMinimum = true
                    }
                    roaringYears.add(yearBigInt)
                } else {
                    if (yearTooBig && k > BigInteger.ONE && !firstTry) {
                        foundMinimum = true
                    } else {
                        if(!firstTry) {
                            ++k
                        }
                    }
                }
            } else {
                if (!firstTry) {
                    foundMinimum = true
                }
            }
            firstTry = false
        }
    }

    return roaringYears.minOrNull()!!
}