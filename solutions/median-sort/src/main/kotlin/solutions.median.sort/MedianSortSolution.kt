interface Judge {
    fun askJudge(numbers: List<Int>): Int
}

class RealJudge: Judge {
    override fun askJudge(numbers: List<Int>): Int {
        println(numbers.joinToString(" "))
        return readLine()!!.toInt()
    }
}

fun main(args: Array<String>) {
    val input = readLine()!!.split(" ").map { it.toInt() }
    val casesNumber = input[0]
    val listSize = input[1]

    for (i in 1..casesNumber) {
        sortList(listSize, RealJudge())
    }
}

fun sortList(listSize: Int, judge: Judge): List<Int> {
    return listOf()
}