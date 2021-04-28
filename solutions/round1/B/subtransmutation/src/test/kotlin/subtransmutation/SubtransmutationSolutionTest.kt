package subtransmutation

import ProblemInput
import findSmallestMetalToProduceAllRequiredUnits
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class SubtransmutationSolutionTest {
    @TestFactory
    fun `given problem input, should compute the smallest metal needed to produce all required units`() =
        listOf(
            ProblemInput(2, listOf(1, 2), listOf(1, 2)) to "4",
            ProblemInput(5, listOf(1, 2), listOf(2, 0, 0, 0, 1)) to "6",
            ProblemInput(3, listOf(1, 2), listOf(1, 1, 1)) to "5",

            ProblemInput(3, listOf(2, 4), listOf(1, 1, 1)) to "IMPOSSIBLE",
            ProblemInput(3, listOf(2, 4), listOf(1, 0, 1)) to "5",
            ProblemInput(5, listOf(2, 5), listOf(1, 0, 0, 0, 1)) to "10",
        ).map { (problemInput, expected) ->
            dynamicTest(
                "given the problem input, should compute the smallest metal needed to produce all required units"
            ) {
                expectThat(findSmallestMetalToProduceAllRequiredUnits(problemInput)).isEqualTo(expected)
            }
        }
}