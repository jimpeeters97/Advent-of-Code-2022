import java.io.File

fun main() {
    val calories = getCalories()
    val topCalories = getTopThreeCalories(calories)

    println("The result of part 1 is: " + getCalories().max())
    println("The result of part 2 is: " + topCalories.sum())
}

fun getCalories(): List<Int> {
    val result = arrayListOf<Int>()

    var currentElfCounter = 0
    File("src/files/day1.txt").forEachLine {
        if (it.isNotEmpty()) {
            currentElfCounter += it.toInt()
        } else {
            result.add(currentElfCounter)
            currentElfCounter = 0
        }
    }
    return result
}

fun getTopThreeCalories(calories: List<Int>): List<Int> {
    return calories.sorted().subList(calories.size - 3, calories.size)
}
