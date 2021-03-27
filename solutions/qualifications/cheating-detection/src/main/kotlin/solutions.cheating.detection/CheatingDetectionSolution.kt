import kotlin.math.exp
import kotlin.math.ln

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

fun computeQuestionsDifficulties(players: List<Player>): List<Double> = List(10000) { it }
    .map { questionNumber -> players.map { it.answers[questionNumber] } }
    .map { it.sum() }
    .map { numberOfGoodAnswersToSkillOrDifficulty(it, 100) }

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

fun probabilityFunctionPrimitive(skill: Double, difficulty: Double): Double = -ln(1 + exp(skill - difficulty))

fun computeProbabilityMean(skill: Double): Double =
    (probabilityFunctionPrimitive(skill, 3.0) - probabilityFunctionPrimitive(skill, -3.0))/ 6.0

fun computeMean(numbers: Sequence<Double>, size: Int): Double = numbers.sum().div(size)

fun computeVariance(mean: Double, numbers: Sequence<Double>, size: Int): Double =
    numbers.map { it * it }.sum().div(size).minus(mean * mean)

fun findCheater(playersAnswers: List<String>): Int {
    val players = playersAnswers.mapIndexed { index, answers -> Player.from(index + 1, answers) }
    val questionsDifficulties = computeQuestionsDifficulties(players)
    return players.filter { it.skill >= 0.0 }
        .map { player ->
                val questionsAnsweredCorrectlyDifficulties = player.answers
                    .asSequence()
                    .mapIndexed { index, answer -> index to answer }
                    .filter { it.second == 1 }
                    .map { it.first }
                    .map { questionsDifficulties[it] }
                val mean  = computeMean(questionsAnsweredCorrectlyDifficulties, player.numberOfGoodAnswers)
                player.number to computeVariance(mean, questionsAnsweredCorrectlyDifficulties, player.numberOfGoodAnswers)
        }
        .maxByOrNull { it.second }!!
        .first
}