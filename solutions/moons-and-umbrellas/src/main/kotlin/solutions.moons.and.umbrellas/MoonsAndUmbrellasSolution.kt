import kotlin.math.min

fun main(args: Array<String>) {
    val casesNumber = readLine()!!.toInt()

    for (i in 1..casesNumber) {
        // Ignore first line of case
        val inputLine = readLine()!!.split(" ")
        println("Case #$i: ${computeMuralCost(ProblemInput(
            inputLine[0].toInt(),
            inputLine[1].toInt(),
            inputLine[2]
        ))}}")
    }
}

fun computeMuralCost(problemInput: ProblemInput): Int {
    var totalCost = 0
    var currentState = State(problemInput.mural[0], false)
    for(drawing in problemInput.mural.subSequence(1, problemInput.mural.length)) {
        val result = computeCost(currentState, drawing, problemInput.cjCost, problemInput.jcCost)
        currentState = result.first
        totalCost += result.second
    }

    return totalCost
}

fun computeCost(state: State, drawing: Char, cjCost: Int, jcCost: Int): Pair<State, Int>  {
    if (state.lastDrawing == '?') {
        return State(drawing, false) to 0
    }

    if(drawing == '?') {
        return State(state.lastDrawing, true) to 0
    }

    val newState = State(drawing, false)

    return newState to computeCost(state.lastDrawing, drawing, cjCost, jcCost)
}

fun computeCost(lastDrawing: Char, drawing: Char, cjCost: Int, jcCost: Int): Int {
    if (lastDrawing == drawing) {
        return 0
    }

    if (lastDrawing == 'C') {
        return cjCost
    }

    return jcCost
}

data class ProblemInput(val cjCost: Int, val jcCost: Int, val mural: String)

data class State(val lastDrawing: Char, val spaceSinceLastDrawing: Boolean)