package solutions.append.sort

import countDigitsRequiredForAppendSort
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo

internal class AppendSortSolutionTest {
    @TestFactory
    fun `given numbers, should count number of digits to add for list to be sorted`() =
        listOf(
            listOf(100, 7, 10) to 4,
            listOf(10, 10) to 1,
            listOf(4, 19, 1) to 2,
            listOf(1, 2, 3) to 0,
            listOf(10, 10, 10) to 2,
            listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1) to 12
        ).map { (numbers, expected) ->
            dynamicTest(
                "given \"$numbers\", " +
                        "should count number of digits (\"$expected\") for list to be sorted"
            ) {
                expectThat(countDigitsRequiredForAppendSort(numbers)).isEqualTo(expected)
            }
        }
}