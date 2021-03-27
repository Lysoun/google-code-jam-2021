package solutions.reverse.sort.engineering

import ProblemInput
import findListMatchingConstraints
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class ReverseSortEngineeringSolutionTest {
    @TestFactory
    fun `given problem input, should give a list of numbers that corresponds to constraints or IMPOSSIBLE, if no such list exists`() =
        listOf(
            ProblemInput(4, 6) to "4 3 2 1",
            ProblemInput(2, 1) to "1 2",
            ProblemInput(7, 12) to "7 6 5 4 3 2 1",
            ProblemInput(7, 2) to "IMPOSSIBLE",
            ProblemInput(2, 1000) to "IMPOSSIBLE",
        ).map { (problemInput, expected) ->
            dynamicTest(
                "given \"$problemInput\", " +
                        "should give a list of numbers that corresponds to constraints or IMPOSSIBLE, if no such list exists"
            ) {
                expectThat(findListMatchingConstraints(problemInput)).isEqualTo(expected)
            }
        }
}