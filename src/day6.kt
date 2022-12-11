import java.io.File

fun main() {
    println("The result of part 1 is: " + findMarker(4))
    println("The result of part 1 is: " + findMarker(14))
}

fun findMarker(characters : Int) : Int {
    var result = 0
    val fullInput = File("src/files/day6.txt").bufferedReader().readLines().joinToString(",")

    fullInput.forEachIndexed { index, _ ->
        if (index + characters <= fullInput.length && result == 0) {
            if (fullInput.substring(index, index + characters).toCharArray().distinct().size == characters) {
                result = index + characters
            }
        }
    }
    return result
}