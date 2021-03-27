package solutions.median.sort

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import sortList
import strikt.api.expectThat
import strikt.assertions.isTrue

internal class MedianSortSolutionTest {
    @ParameterizedTest
    @ValueSource(strings = ["5 4 3 2 1", "1 3 5 4 2"])
    fun `given `(input: String) {
        // Given
        val goodAnswer = input.split(" ").map { it.toInt() }
        val judge = TestJudge(goodAnswer)

        // When
        val answer = sortList(5, judge)
        val isAnswerCorrect = answer == goodAnswer || answer == goodAnswer.reversed()

        // Then
        expectThat(isAnswerCorrect).isTrue()
    }
}