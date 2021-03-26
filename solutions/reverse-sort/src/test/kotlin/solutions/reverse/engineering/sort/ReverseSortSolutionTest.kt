package solutions.reverse.engineering.sort

import computeReverseCostToSortList
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class ReverseSortSolutionTest {
    @TestFactory
    fun `given numbers, should count number of applications of the Reverse function in order to sort the list`() =
        listOf(
            listOf(4, 2, 1, 3) to 6,
            listOf(1, 2) to 1,
            listOf(7, 6, 5, 4, 3, 2, 1) to 12
        ).map { (numbers, expected) ->
            dynamicTest(
                "given \"$numbers\", " +
                        "should count number of applications (\"$expected\") of the Reverse function in order to sort the list"
            ) {
                expectThat(computeReverseCostToSortList(numbers)).isEqualTo(expected)
            }
        }
}