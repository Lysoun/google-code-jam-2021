package broken.clock

import Time
import computeTime
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigInteger

internal class BrokenClockSolutionTest {
    @TestFactory
    fun `given clock angles, should compute the time`() =
        listOf(
            listOf(0, 0, 0) to Time(0, 0, 0, 0),
            listOf(0, 21600000000000, 23400000000000) to Time(6, 30, 0, 0),
            listOf(1476000000000, 2160000000000, 3723000000000) to Time(1, 2, 3, 0),
                listOf(0, 7200000000000, 10800000000000) to Time(2, 0, 0, 0),

                listOf(5400000000000, 5400000000000, 5400000000000) to Time(0, 0, 0, 0),
                listOf(10800000000000, 32400000000000, 32400000000000) to Time(0, 30, 0, 0),
                listOf(23076000000000, 23076000000000, 25323000000000) to Time(1, 2, 3, 0),

                listOf(0, 11, 179) to Time(0, 0, 0, 1)
        ).map { (numbers, expected) ->
            dynamicTest(
                "given the clock angles \"$numbers\", " +
                        "should compute the time (\"$expected\")"
            ) {
                expectThat(computeTime(numbers.map { BigInteger.valueOf(it.toLong()) })).isEqualTo(expected)
            }
        }
}