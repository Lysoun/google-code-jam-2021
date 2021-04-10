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
            listOf(1000000, 1) to 6,
            listOf(1000009, 1000008, 1000007, 1000006) to 6,
            List(100) { 1 } to 188,
            listOf(100, 7, 10) to 4,
            listOf(10, 10) to 1,
            listOf(4, 19, 1) to 2,
            listOf(1, 2, 3) to 0,
            listOf(10, 10, 10) to 2,
            listOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1) to 12,
            listOf(70, 1) to 2,
            listOf(10, 7) to 1,
            listOf(1000, 100, 10) to 3
        ).map { (numbers, expected) ->
            dynamicTest(
                "given \"$numbers\", " +
                        "should count number of digits (\"$expected\") for list to be sorted"
            ) {
                expectThat(countDigitsRequiredForAppendSort(numbers.map { it.toBigInteger() })).isEqualTo(expected)
            }
        }
}