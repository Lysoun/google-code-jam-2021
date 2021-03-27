package solutions.median.sort

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import sortList
import strikt.api.expectThat
import strikt.assertions.isTrue

internal class MedianSortSolutionTest {
    @ParameterizedTest
    @ValueSource(strings = ["5 4 3 2 1", "1 3 5 4 2", "1 2 3 4 5 6", "4 5 3 2 6 1"])
    fun `given `(input: String) {
        // Given
        val goodAnswer = input.split(" ").map { it.toInt() }
        val judge = TestJudge(goodAnswer)

        // When
        val answer = sortList(goodAnswer.size, judge)
        val isAnswerCorrect = answer == goodAnswer || answer == goodAnswer.reversed()

        // Then
        expectThat(isAnswerCorrect).isTrue()
    }
}