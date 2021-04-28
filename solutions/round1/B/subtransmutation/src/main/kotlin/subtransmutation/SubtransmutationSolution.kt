import java.lang.Integer.max

data class ProblemInput(val metalTypesNumber: Int, val spells: List<Int>, val unitsRequired: List<Int>)

fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        val line1 = readLine()!!.split(" ").map { it.toInt() }
        val line2 = readLine()!!.split(" ").map { it.toInt() }
        println(
            "Case #$i: ${
                findSmallestMetalToProduceAllRequiredUnits(
                    ProblemInput(
                        line1[0],
                        line1.subList(1, 3),
                        line2
                    )
                )
            }"
        )
    }
}

fun applySpells(spells: List<Int>, metal: Int): List<Int> = spells.map { metal - it }.filter { it >= 0 }

fun applySpells(spells:List<Int>, metalUnits: MutableList<Int>, unitsRequired: List<Int>): MutableList<Int> {
    var found = false
    var biggestMetalToTransmutate = metalUnits.size - 1

    while(biggestMetalToTransmutate >= 0 && ! found) {
        if(metalUnits[biggestMetalToTransmutate] > 0 &&
            (biggestMetalToTransmutate >= unitsRequired.size ||
                    metalUnits[biggestMetalToTransmutate] > unitsRequired[biggestMetalToTransmutate])) {
            found = true
        } else {
            --biggestMetalToTransmutate
        }
    }

    if (biggestMetalToTransmutate < 0) {
        return metalUnits
    }

    applySpells(spells, biggestMetalToTransmutate).forEach { ++metalUnits[it] }
    --metalUnits[biggestMetalToTransmutate]
    return metalUnits
}

const val IMPOSSIBLE = "IMPOSSIBLE"

fun findSmallestMetalToProduceAllRequiredUnits(problemInput: ProblemInput): String {
    if(problemInput.spells.all { it % 2 == 0 } &&
        problemInput.unitsRequired.filterIndexed {index: Int, i: Int -> index % 2 == 1 && i > 0 }.isNotEmpty()) {
        return IMPOSSIBLE
    }

    var i = 0
    var found = false
    while(!found) {
        var lastMetalUnits: List<Int> = listOf()
        var metalUnits = MutableList(max(problemInput.metalTypesNumber, i + 1)) {
            if(it == i) {
                 1
            }
            else {
                 0
            }
        }
        while(lastMetalUnits != metalUnits && !found) {
            if (metalUnits
                    .filterIndexed {index: Int, i: Int -> index < problemInput.metalTypesNumber && i < problemInput.unitsRequired[index] }
                    .isEmpty()) {
                found = true
            }

            if(!found) {
                lastMetalUnits = metalUnits.toList()
                metalUnits = applySpells(problemInput.spells, metalUnits, problemInput.unitsRequired)
            }
        }

        if(!found) {
            ++i
        }
    }

    return (i + 1).toString()
}