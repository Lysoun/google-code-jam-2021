package solutions.prime.time

import computeMaximumScore
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class PrimeTimeSolutionTest {
    @TestFactory
    fun `given deck, should compute the maximum score achievable`() =
        listOf(
            listOf(2, 2, 3, 5, 5, 7, 11) to 25,
            listOf(17, 17) to 17,
            listOf(2, 2, 3) to 0,
            listOf(2, 2, 2, 2, 2, 2, 2) to 8
        ).map { (numbers, expected) ->
            dynamicTest(
                "given \"$numbers\", " +
                        "should compute the maximum score achievable (\"$expected\")"
            ) {
                expectThat(computeMaximumScore(numbers)).isEqualTo(expected)
            }
        }
}