import java.math.BigInteger

fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        // Ignore list size
        readLine()
        println("Case #$i: ${countDigitsRequiredForAppendSort(readLine()!!.split(" ").map { it.toBigInteger() })}")
    }
}

fun countDigitsRequiredForAppendSort(list: List<BigInteger>): Int {
    var digitsRequired = 0
    var digitsToAdd: String
    val sortedList = mutableListOf(list[0])
    var last = list[0]

    for (i in 1 until list.size) {
        var elt = list[i]
        digitsToAdd = diff(last, elt)
        elt = (elt.toString() + digitsToAdd).toBigInteger()
        digitsRequired += digitsToAdd.length
        last = elt
        sortedList.add(last)
    }

    return digitsRequired
}

fun diff(int1: BigInteger, int2: BigInteger): String {
    if (int1 < int2) {
        return ""
    }

    if(int1 == int2) {
        return "0"
    }

    val str1 = int1.toString()
    val str2 = int2.toString()

    val lengthDifference = str1.length - str2.length
    val startOfStr1 = str1.substring(0, str2.length).toBigInteger()

    if (startOfStr1 > int2) {
        return "0".repeat(lengthDifference + 1)
    }

    if (startOfStr1 < int2) {
        return "0".repeat(lengthDifference)
    }

    return incrementDigitsToAdd(str1.substring(str2.length, str1.length))
}

fun incrementDigitsToAdd(digitsToAdd: String): String {
    return if (digitsToAdd == "") {
        "0"
    } else {
        if (digitsToAdd.all { it == '9' }) {
            "0".repeat(digitsToAdd.length + 1)
        } else {
            val result = (digitsToAdd.toBigInteger() + BigInteger.ONE).toString()
            "0".repeat(digitsToAdd.length - result.length) + result
        }
    }
}