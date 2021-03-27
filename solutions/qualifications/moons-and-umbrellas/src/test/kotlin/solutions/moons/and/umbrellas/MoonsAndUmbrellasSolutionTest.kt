package solutions.moons.and.umbrellas

import ProblemInput
import computeMuralCost
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class MoonsAndUmbrellasSolutionTest {
    @TestFactory
    fun `given problem input, should compute the smallest cost for the mural`() =
        listOf(
            ProblemInput(2, 3, "CJ?CC?") to 5,
            ProblemInput(4, 2, "CJCJ") to 10,
            ProblemInput(1, 3, "C?J") to 1,
            ProblemInput(2, 5, "??J???") to 0,
            ProblemInput(2, -5, "??JJ??") to -8,
            ProblemInput(2, -5, "??JJ???") to -11
        ).map { (problemInput, expected) ->
            dynamicTest(
                "given \"$problemInput\", " +
                        "should compute the smallest cost (\"$expected\") for the mural"
            ) {
                expectThat(computeMuralCost(problemInput)).isEqualTo(expected)
            }
        }
}