import java.io.File

fun main() {
    val pairs = getAssignmentPairs()

    println("The result of part 1 is: " + getOverlappingPairs(pairs, true).size)
    println("The result of part 2 is: " + getOverlappingPairs(pairs, false).size)
}

fun getAssignmentPairs(): List<Pair<String, String>> {
    val result = arrayListOf<Pair<String, String>>()
    File("src/files/day4.txt").forEachLine {
        result.add(Pair(it.substringBefore(","), it.substringAfter(",")))
    }
    return result
}

fun getOverlappingPairs(pairs: List<Pair<String, String>>, fullyContains : Boolean): List<Pair<String, String>> {
    val result = arrayListOf<Pair<String, String>>()

    for (pair in pairs) {
        val firstElfRangeBegin = pair.first.substringBefore("-").toInt()
        val firstElfRangeEnd = pair.first.substringAfter("-").toInt()
        val secondElfRangeBegin = pair.second.substringBefore("-").toInt()
        val secondElfRangeEnd = pair.second.substringAfter("-").toInt()

        if (fullyContains) {
            if ((secondElfRangeBegin >= firstElfRangeBegin && secondElfRangeEnd <= firstElfRangeEnd) ||
                    (firstElfRangeBegin >= secondElfRangeBegin && firstElfRangeEnd <= secondElfRangeEnd)) {
                result.add(pair)
            }
        } else {

            if (((secondElfRangeBegin in firstElfRangeBegin..firstElfRangeEnd) ||
                            (secondElfRangeEnd in firstElfRangeBegin..firstElfRangeEnd)) ||
                    (firstElfRangeBegin in secondElfRangeBegin..secondElfRangeEnd || firstElfRangeEnd in
                            secondElfRangeBegin..secondElfRangeEnd)) {
                result.add(pair)
            }
        }
    }
    return result
}