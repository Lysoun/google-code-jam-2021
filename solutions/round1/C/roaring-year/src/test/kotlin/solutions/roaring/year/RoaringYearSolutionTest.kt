package solutions.roaring.year

import findNextRoaringYear
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.math.BigInteger

internal class RoaringYearSolutionTest {
    @TestFactory
    fun `given current year, should find next roaring year`() =
        listOf(
            "2020" to 2021,
            "2021" to 2122,
            "68000" to 78910,
            "101" to 123
        ).map { (currentYear, nextRoaringYear) ->
            dynamicTest(
                "given current year, \"$currentYear\", " +
                        "should find next roaring year (\"$nextRoaringYear\")"
            ) {
                expectThat(findNextRoaringYear(currentYear)).isEqualTo(BigInteger.valueOf(nextRoaringYear.toLong()))
            }
        }
}