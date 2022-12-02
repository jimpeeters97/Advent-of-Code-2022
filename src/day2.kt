import java.io.File

fun main() {
    val scores1 = mutableListOf<Int>()
    val scores2 = mutableListOf<Int>()

    File("src/files/day2.txt").forEachLine {
        val opponent = it.substringBefore(" ")
        val me = it.substringAfter(" ")

        scores1.add(getScore1(getShape(opponent), getShape(me)))
        scores2.add(getScore2(getShape(opponent), me))
    }

    println("The result of part 1 is: " + scores1.sum())
    println("The result of part 2 is: " + scores2.sum())
}

fun getScore1(opponent: String, me: String) : Int {
    val scoreMap = mutableMapOf<String, Int>()
    scoreMap["Rock"] = 1
    scoreMap["Paper"] = 2
    scoreMap["Scissor"] = 3

    var result = 0

    if (opponent == me) {
        result = 3
    } else if ((opponent == "Rock" && me == "Paper") ||
        (opponent == "Paper" && me == "Scissor") ||
        (opponent == "Scissor" && me == "Rock")) {
        result = 6
    }

    return result + (scoreMap[me] ?: 0)
}

fun getScore2(opponent: String, wantedResult: String) : Int {
    when (wantedResult) {
        "X" -> {
            return when (opponent) {
                "Rock" -> { 3 }
                "Scissor" -> { 2 }
                else -> { 1 }
            }
        }
        "Y" -> {
            return when (opponent) {
                "Rock" -> { 4 }
                "Paper" -> { 5 }
                else -> { 6 }
            }
        }
        else -> {
            return when (opponent) {
                "Scissor" -> { 7 }
                "Rock" -> { 8 }
                else -> { 9 }
            }
        }
    }
}

fun getShape(code: String) : String {
    return if (code == "A" || code == "X") {
        "Rock"
    } else if (code == "B" || code == "Y") {
        "Paper"
    } else {
        "Scissor"
    }
}