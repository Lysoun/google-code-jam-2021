package solutions.roaring.year

import findNextRoaringYear
import org.junit.jupiter.api.DynamicTest.dynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import strikt.api.expectThat
import strikt.assertions.isEqualTo
import java.io.File
import java.math.BigInteger

internal class RoaringYearSolutionTest {
    @TestFactory
    fun `given current year, should find next roaring year`() =
        listOf(
            "2020" to 2021,
            "2021" to 2122,
            "68000" to 78910,
            "101" to 123,
            "9999999" to 10001001,
            "1234566" to 1234567,
            "789" to 910,
            "1" to 12,
            "9" to 12,
            "99" to 123,
            "67" to 78,
            "123" to 234,
            "1999" to 2021,
            "10001000" to 10001001,
            "20212021" to 20212022,
            "9898000" to 9899100,
            "333333" to 333334,
            "333434" to 333435,
            "33333" to 34567,
            "1000000" to 1234567,
            "100000" to 100101,
            "123124" to 123456,
            "123456" to 124125,
            "199200" to 200201,
            "998999" to 1234567

        ).map { (currentYear, nextRoaringYear) ->
            dynamicTest(
                "given current year, \"$currentYear\", " +
                        "should find next roaring year (\"$nextRoaringYear\")"
            ) {
                expectThat(findNextRoaringYear(currentYear)).isEqualTo(BigInteger.valueOf(nextRoaringYear.toLong()))
            }
        }

    @Test
    fun `run case 1`() {
        val testFileInput = File("./src/test/resources/ts1_input.txt").bufferedReader()
        val testFileOutput = File("./src/test/resources/ts1_output.txt").bufferedReader()


        val nbOfCase = testFileInput.readLine()!!.toInt()

        for (i in 0 until nbOfCase) {
            val input = testFileInput.readLine()!!
            val output = BigInteger(testFileOutput.readLine()!!.split(" ")[2])
            val solution = findNextRoaringYear(input)

            println("Case #${i + 1}: input: $input solution: $solution expected: $output")
            expectThat(solution).isEqualTo(output)

        }

    }
}