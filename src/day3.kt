import java.io.File

fun main() {
    println("The result of part 1 is: " + getPriorities1().sum())
    println("The result of part 2 is: " + getPriorities2().sum())
}

fun getPriorities1() : List<Int> {
    val result = arrayListOf<Int>()

    File("src/files/day3.txt").forEachLine {
        val firstComp = it.substring(0, it.length / 2)
        val secondComp = it.substring((it.length / 2))
        var prio = 0

        val charSet = mutableSetOf<Char>()

        for (char in firstComp) {
            if (secondComp.contains(char)) {
                charSet.add(char)
            }
        }

        for (sharedChar in charSet) {
            prio += getPrio(sharedChar)
        }
        result.add(prio)
    }
    return result
}

fun getPriorities2() : List<Int> {
    val result = arrayListOf<Int>()

    var idx = 0
    val rucksakList = mutableListOf<String>()
    File("src/files/day3.txt").forEachLine {

        if (idx < 3) {
            rucksakList.add(it)
            idx++
        }

        if (idx == 3) {
            for (char in rucksakList[0]) {
                if (rucksakList[1].contains(char) && rucksakList[2].contains(char)) {
                    result.add(getPrio(char))
                    break
                }
            }
            idx = 0
            rucksakList.clear()
        }
    }
    return result
}

fun getPrio(char: Char) : Int {
    val alphabet = "abcdefghijklmnopqrstuvwxyz"

    if (char.isUpperCase()) {
        return (alphabet.indexOf(char.lowercaseChar()) + 1) + 26
    }
    return (alphabet.indexOf(char) + 1)
}
