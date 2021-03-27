package solutions.median.sort

import Judge

class TestJudge(private val goodAnswer: List<Int>) : Judge {
    override fun askJudge(numbers: List<Int>): Int {
        if(numbers.size > 3) {
            return if(numbers == goodAnswer || numbers == goodAnswer.reversed()) { 1 } else { 0 }
        }

        return numbers
            .map { it to goodAnswer.indexOf(it) }
            .sortedBy { it.second }
            .map { it.first }[1]
    }
}