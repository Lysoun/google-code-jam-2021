fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    // Ignore required percentage to success
    readLine()

    for (i in 1..casesNumber) {
        println("Case #$i: ${findCheater(List(100) { 0 }.map { readLine()!! })}")
    }
}

fun numberOfGoodAnswersToSkillOrDifficulty(numberOfGoodAnswers: Int, totalNumber: Int): Double {
    return (6.0 * numberOfGoodAnswers / totalNumber) - 3
}

data class Player(val number: Int, val answers: List<Int>, val numberOfGoodAnswers: Int, val skill: Double) {
    companion object {
        fun from(number: Int, playerAnswers: String): Player {
            val playersAnswersAsShorts = playerAnswers.toList()
                .map { answer -> Character.getNumericValue(answer) }
            val numberOfGoodAnswers = playersAnswersAsShorts.sum()

            val skill = numberOfGoodAnswersToSkillOrDifficulty(numberOfGoodAnswers, 10000)

            return Player(number, playersAnswersAsShorts, numberOfGoodAnswers, skill)
        }
    }
}

fun findCheater(playersAnswers: List<String>): Int {
    val players = playersAnswers.mapIndexed { index, answers -> Player.from(index + 1, answers) }

    return players.maxByOrNull { it.numberOfGoodAnswers }!!.number
}