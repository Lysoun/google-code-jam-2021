package solutions.append.sort

import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import countDigitsRequiredForAppendSort

internal class AppendSortSolutionTest {
    @TestFactory
    fun `given numbers, should count number of applications of the Reverse function in order to sort the list`() =
        listOf(
            listOf(100, 7, 10) to 4,
            listOf(10, 10) to 1,
            listOf(4, 19, 1) to 2,
            listOf(1, 2, 3) to 0
        ).map { (numbers, expected) ->
            dynamicTest(
                "given \"$numbers\", " +
                        "should count number of applications (\"$expected\") of the Reverse function in order to sort the list"
            ) {
                expectThat(countDigitsRequiredForAppendSort(numbers)).isEqualTo(expected)
            }
        }
}