package solutions.moons.and.umbrellas

import ProblemInput
import computeMuralCost
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class MoonsAndUmbrellasSolutionTest {
    @TestFactory
    fun `given numbers, should count number of applications of the Reverse function in order to sort the list`() =
        listOf(
            ProblemInput(2, 3, "CJ?CC?") to 5,
            ProblemInput(4, 2, "CJCJ") to 10,
            ProblemInput(1, 3, "C?J") to 1,
            ProblemInput(2, 5, "??J???") to 0,
            ProblemInput(2, -5, "??JJ??") to -8
        ).map { (problemInput, expected) ->
            dynamicTest(
                "given \"$problemInput\", " +
                        "should count number of applications (\"$expected\") of the Reverse function in order to sort the list"
            ) {
                expectThat(computeMuralCost(problemInput)).isEqualTo(expected)
            }
        }
}