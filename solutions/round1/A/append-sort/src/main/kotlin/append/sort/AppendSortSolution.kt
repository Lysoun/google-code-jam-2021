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
    var digitsToAdd = ""
    var last = sortedList[0]

    for(i in 1 until list.size) {
        var elt = sortedList[i]

        if (elt != last) {
            last = elt
            digitsToAdd = ""
        }

        while (elt <= sortedList[i - 1]) {
            elt = (sortedList[i].toString() + digitsToAdd).toInt()

            if (elt <= sortedList[i - 1]) {
                digitsToAdd = if (digitsToAdd == "") {
                    "0"
                } else {
                    if (digitsToAdd[digitsToAdd.length - 1] == '9') {
                        digitsToAdd.substring(0, digitsToAdd.length - 1) + "00"
                    } else {
                        digitsToAdd.substring(
                            0,
                            digitsToAdd.length - 1
                        ) + (Character.getNumericValue(digitsToAdd[digitsToAdd.length - 1]) + 1).toString()
                    }
                }
            }
        }

        if (elt != sortedList[i]) {
            digitsRequired += digitsToAdd.length
            sortedList[i] = elt
        }
    }

    return digitsRequired
}