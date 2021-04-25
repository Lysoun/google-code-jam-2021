import java.math.BigInteger
import java.math.BigInteger.*

fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        val time = computeTime(readLine()!!.split(" ").map { it.toBigInteger() })
        println("Case #$i: ${time.hour} ${time.minutes} ${time.seconds} ${time.nanoseconds}")
    }
}

val NANOSECONDS_IN_ONE_HOUR = valueOf(60) * valueOf(60) * valueOf(10).pow(9)
val TICKS_IN_ONE_WHOLE_REVOLUTION_FOR_SECONDS_HAND = valueOf(720) * NANOSECONDS_IN_ONE_HOUR
val TICKS_IN_ONE_WHOLE_REVOLUTION_FOR_MINUTES_HAND = valueOf(12) * NANOSECONDS_IN_ONE_HOUR
val TICKS_IN_ONE_WHOLE_REVOLUTION_FOR_HOURS_HAND = NANOSECONDS_IN_ONE_HOUR

fun findAllClockRotations(angles: List<BigInteger>): ClockHandsAngles {
    var currentAngles = angles
    var possibleSeconds: List<BigInteger>
    var possibleMinutes: List<BigInteger>
    var ticks = ZERO

    while(ticks < TICKS_IN_ONE_WHOLE_REVOLUTION_FOR_SECONDS_HAND) {
        possibleMinutes = currentAngles
                .filter { it.mod(valueOf(12)).equals(ZERO) }
                .map { it.mod(TICKS_IN_ONE_WHOLE_REVOLUTION_FOR_MINUTES_HAND) }
        possibleSeconds = possibleMinutes
                .filter { it.mod(valueOf(720)).equals(ZERO) }
                .map { it.mod(TICKS_IN_ONE_WHOLE_REVOLUTION_FOR_SECONDS_HAND) }
        if(possibleSeconds.size > 1 && possibleMinutes.isNotEmpty()) {
           val clockHandsAngles = computeClockHandsAngles(RotatedClock(possibleSeconds, possibleMinutes, currentAngles.map { it.mod(TICKS_IN_ONE_WHOLE_REVOLUTION_FOR_HOURS_HAND) }))
           if(clockHandsAngles.hour != valueOf(-1)) {
               return clockHandsAngles
           }
        }
        currentAngles = currentAngles.map { it.add(ONE) }
        ++ticks
    }

    return ClockHandsAngles(valueOf(-1), ZERO, ZERO)
}

fun computeTime(clockHandsAngles: ClockHandsAngles): Time {
    return Time(ticksToHours(clockHandsAngles.hour), ticksToMinutes(clockHandsAngles.minutes), ticksToSeconds(clockHandsAngles.seconds), clockHandsAngles.hour.mod(valueOf(10).pow(9)).toInt())
}

fun ticksToSeconds(ticks: BigInteger): Int {
    return ticks.divide(valueOf(720).multiply(valueOf(10).pow(9))).toInt()
}

fun ticksToMinutes(ticks: BigInteger): Int {
    return ticks.divide(valueOf(60).multiply(valueOf(10).pow(9))).divide(valueOf(12)).toInt()
}

fun ticksToHours(ticks: BigInteger): Int {
    return ticks.divide(valueOf(3600).multiply(valueOf(10).pow(9))).toInt()
}

fun computeClockHandsAngles(rotatedClock: RotatedClock): ClockHandsAngles {
        val possibleSeconds = rotatedClock.possibleSeconds
        for (s in possibleSeconds) {
            val possibleMinutes = rotatedClock.possibleMinutes.filterIndexed { index, _ -> index != rotatedClock.possibleMinutes.indexOf(s) }

            for (m in possibleMinutes) {
                val possibleHours = rotatedClock.possibleHours.filterIndexed { index, _ -> index != rotatedClock.possibleHours.indexOf(m) && index != rotatedClock.possibleHours.indexOf(s) }

                for (h in possibleHours) {
                    val nanosecondsAfterHourGraduation = h.mod(valueOf(10).pow(9).multiply(valueOf(3600)))
                    val truc = nanosecondsAfterHourGraduation.subtract(m.divide(valueOf(12)))
                    val bidule = m.mod(valueOf(10).pow(9).multiply(valueOf(60)).multiply(valueOf(12))).divide(valueOf(12)).subtract(s.divide(valueOf(720)))
                    if (truc >= ZERO && truc < valueOf(10).pow(9).multiply(valueOf(60)) && bidule >= ZERO && bidule < valueOf(10).pow(9)) {
                        return ClockHandsAngles(h, m, s)
                    }
                }
            }
        }

    return ClockHandsAngles(valueOf(-1), ZERO, ZERO)
}

fun computeTime(input: List<BigInteger>): Time {
    return computeTime(findAllClockRotations(input))
}

data class RotatedClock(val possibleSeconds: List<BigInteger>, val possibleMinutes: List<BigInteger>, val possibleHours: List<BigInteger>)

data class ClockHandsAngles(val hour: BigInteger, val minutes: BigInteger, val seconds: BigInteger)

data class Time(val hour: Int, val minutes: Int, val seconds: Int, val nanoseconds: Int)